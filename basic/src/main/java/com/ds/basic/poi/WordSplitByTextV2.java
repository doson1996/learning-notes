package com.ds.basic.poi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.aspose.words.Body;
import com.aspose.words.Document;
import com.aspose.words.Node;
import com.aspose.words.NodeCollection;
import com.aspose.words.NodeType;
import com.aspose.words.Paragraph;
import com.aspose.words.SaveFormat;
import com.aspose.words.Section;
import com.aspose.words.Shape;
import com.aspose.words.Table;

/**
 * @author ds
 * @date 2026/7/31
 * @description 根据标题拆分，再判断有没有内容(内网初始版本)
 */
public class WordSplitByTextV2 {

    public static void main(String[] args) throws Exception {
        // ========== 配置区域 ==========
        String srcPath = "D:\\haizhi\\doc\\2025\\03.尽调\\数字分析助手\\报告模板\\小微公司授信业务调查报告.docx";               // 源文档
        String outputDir = "D:\\haizhi\\doc\\2025\\03.尽调\\数字分析助手\\报告模板\\output";                // 输出目录

        // 作为拆分依据的标题文本（按文档出现顺序给出）
        // 如果文档中出现多次相同文本，也只按第一次匹配处理，可按需要调整
        String[] headingTexts = {"一、经营机构授信申请", "二、申请人基本情况调查", "三、申请人经营情况调查", "四、申请人信用情况调查",
                "五、申请人财务情况调查", "六、项目调查（针对项目贷款，非项目贷款不需填写）（如果非项目贷款，自动隐藏该部分内容）", "七、还款来源分析",
                "八、授信风险分析及风险控制措施", "九、其它补充情况：", "十、调查结论（授信品种、授信用途、授信使用方式、利率/保证金比例、担保方式、还款方式等）",
                "XXXX小微公司授信业务调查报告撰写规范"
        };
        // ============================

        splitByHeadingText(srcPath, outputDir, headingTexts);
    }

    /**
     * 根据指定的标题文本列表拆分 Word 文档
     */
    public static void splitByHeadingText(String srcPath, String outputDir,
                                          String[] headingTexts) throws Exception {
        Document doc = new Document(srcPath);
        File dir = new File(outputDir);
        if (!dir.exists()) dir.mkdirs();

        // 1. 收集所有匹配的标题段落（按文档顺序）
        List<Paragraph> headingParas = new ArrayList<>();
        NodeCollection<Paragraph> allParas = doc.getChildNodes(NodeType.PARAGRAPH, true);

        for (Paragraph para : allParas) {
            String text = para.toString(SaveFormat.TEXT).trim();
            for (String hText : headingTexts) {
                if (text.equals(hText)) {     // 精确匹配；如需包含关系可改用 contains
                    headingParas.add(para);
                    break;
                }
            }
        }

        if (headingParas.isEmpty()) {
            System.out.println("未找到任何匹配的标题文本，无法拆分。");
            return;
        }

        // 关键：按文档全局顺序收集所有 Section 的 Body 顶层节点。
        // 原实现用 start.getNextSibling() 遍历，只能停留在同一个 Section 内，
        // 封面单独成节时，第一个章节就只截到封面。
        List<Node> blocks = collectTopLevelBlocks(doc);
        System.out.println("源文档节数=" + doc.getSections().getCount()
                + "，匹配标题数=" + headingParas.size() + "，顶层节点数=" + blocks.size());

        int savedCount = 0;
        int skippedCount = 0;

        // 2. 按标题位置拆分并保存
        for (int i = 0; i < headingParas.size(); i++) {
            Paragraph heading = headingParas.get(i);
            Node start = heading;
            if (i == 0) {
                // 第一个章节从文档最开头（封面）开始，跨节也要带上正文
                start = blocks.isEmpty() ? heading : blocks.get(0);
            }

            Node end = (i + 1 < headingParas.size()) ? headingParas.get(i + 1) : null;

            Document newDoc = extractContent(doc, start, end, blocks);
            // 文件名和空章节判断始终用章节标题，不能用封面首段
            String titleText = heading.toString(SaveFormat.TEXT).trim();

            // ★ 检测空章节（排除标题自身）
            if (isSectionEmpty(newDoc, titleText)) {
                System.out.println("⚠️ 空章节（无内容），跳过保存: " + titleText);
                skippedCount++;
                continue;
            }

            // 文件名处理：移除非法字符
            String cleanTitle = titleText.replaceAll("[\\\\/:*?\"<>|]", "").trim();
            String fileName = cleanTitle.isEmpty() ? "part_" + (i + 1) : cleanTitle;
            String outputPath = outputDir + File.separator + fileName + ".docx";

            newDoc.save(outputPath);
            System.out.println("已生成: " + outputPath);
            savedCount++;
        }

        System.out.println("拆分完成 → 生成 " + savedCount + " 个文档，跳过 " + skippedCount + " 个空章节。");
    }

    /**
     * 收集整个文档中所有 Section 的 Body 直接子节点，按文档顺序排列。
     */
    private static List<Node> collectTopLevelBlocks(Document srcDoc) {
        List<Node> blocks = new ArrayList<>();
        for (Section section : srcDoc.getSections()) {
            Body body = section.getBody();
            if (body == null) continue;
            for (Node child = body.getFirstChild(); child != null; child = child.getNextSibling()) {
                blocks.add(child);
            }
        }
        return blocks;
    }

    /**
     * 找到 node 所属的顶层 block（Body 的直接子节点）在 blocks 中的下标。
     * 标题位于表格单元格内时，返回其所属的最外层表格的下标。
     */
    private static int indexOfTopLevelBlock(List<Node> blocks, Node node) {
        Node top = node;
        while (top != null && top.getParentNode() != null
                && top.getNodeType() != NodeType.SECTION
                && top.getNodeType() != NodeType.DOCUMENT
                && top.getParentNode().getNodeType() != NodeType.BODY) {
            top = top.getParentNode();
        }
        if (top == null || top.getNodeType() == NodeType.SECTION
                || top.getNodeType() == NodeType.DOCUMENT) {
            return -1;
        }
        for (int i = 0; i < blocks.size(); i++) {
            if (blocks.get(i) == top) return i;
        }
        return -1;
    }

    /**
     * 提取 start 到 end（不含 end）之间的顶层节点，生成独立文档。
     * 基于全局 block 列表按位置切片，可跨越 Section 分节符。
     */
    private static Document extractContent(Document srcDoc, Node start, Node end,
                                           List<Node> blocks) throws Exception {
        // 克隆文档结构（样式、主题、列表等），不复制正文
        Document dstDoc = (Document) srcDoc.deepClone(false);

        Section dstSection = dstDoc.getFirstSection();
        if (dstSection == null) {
            dstSection = new Section(dstDoc);
            dstDoc.appendChild(dstSection);
        }
        Body dstBody = dstSection.getBody();
        if (dstBody == null) {
            dstBody = new Body(dstDoc);
            dstSection.appendChild(dstBody);
        }
        dstBody.removeAllChildren();

        int startIdx = indexOfTopLevelBlock(blocks, start);
        int endIdx = (end == null) ? blocks.size() : indexOfTopLevelBlock(blocks, end);
        if (startIdx < 0) startIdx = 0;
        if (endIdx < startIdx) endIdx = blocks.size();

        for (int i = startIdx; i < endIdx; i++) {
            dstBody.appendChild(dstDoc.importNode(blocks.get(i), true));
        }

        // 保证至少有一个段落
        if (dstBody.getChildNodes().getCount() == 0) {
            dstBody.appendChild(new Paragraph(dstDoc));
        }

        return dstDoc;
    }

    /**
     * 判断拆分后的章节是否没有实质内容（即只有标题）。
     * 检测范围：文字、表格、图片。
     *
     * @param doc       已生成的章节文档
     * @param titleText 标题文本（用于排除标题段落自身）
     * @return true 表示空章节
     */
    private static boolean isSectionEmpty(Document doc, String titleText) throws Exception {
        // 1. 检查段落文字（跳过标题自身）
        NodeCollection<Paragraph> paragraphs = doc.getChildNodes(NodeType.PARAGRAPH, true);
        for (Paragraph p : paragraphs) {
            String text = p.toString(SaveFormat.TEXT).trim();
            if (text.equals(titleText)) {
                continue;   // 忽略标题段落
            }
            if (!text.isEmpty()) {
                return false;   // 发现非标题文字，不算空
            }
        }

        // 2. 检查表格（只要有表格就算有内容，无论单元格是否为空）
        NodeCollection<Table> tables = doc.getChildNodes(NodeType.TABLE, true);
        if (tables.getCount() > 0) {
            return false;
        }

        // 3. 检查图片（Shape 涵盖所有图片/图形，无需 DRAWING_ML）
        NodeCollection<Shape> shapes = doc.getChildNodes(NodeType.SHAPE, true);
        for (Shape shape : shapes) {
            if (shape.hasImage()) {
                return false;   // 存在图片
            }
        }
        return true;  // 以上皆无，判定为空
    }

}
