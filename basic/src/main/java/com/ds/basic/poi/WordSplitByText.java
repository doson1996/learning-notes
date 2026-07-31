package com.ds.basic.poi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.aspose.words.Document;
import com.aspose.words.Node;
import com.aspose.words.NodeCollection;
import com.aspose.words.NodeType;
import com.aspose.words.Paragraph;
import com.aspose.words.SaveFormat;
import com.aspose.words.Section;

/**
 * @author ds
 * @date 2026/7/31
 * @description 根据标题拆分
 */
public class WordSplitByText {

    public static void main(String[] args) throws Exception {
        // ========== 配置区域 ==========
        String srcPath = "D:\\haizhi\\doc\\2025\\03.尽调\\数字分析助手\\报告模板\\小微公司授信业务调查报告.docx";               // 源文档
        String outputDir = "D:\\haizhi\\doc\\2025\\03.尽调\\数字分析助手\\报告模板\\";                // 输出目录

        // 作为拆分依据的标题文本（按文档出现顺序给出）
        // 如果文档中出现多次相同文本，也只按第一次匹配处理，可按需要调整
        String[] headingTexts = {"一、经营机构授信申请", "二、申请人基本情况调查", "三、申请人经营情况调查"};
        // ============================

        splitByHeadingText(srcPath, outputDir, headingTexts);
    }

    /**
     * 根据指定的标题文本列表拆分 Word 文档
     * @param srcPath       源文档路径
     * @param outputDir     输出目录
     * @param headingTexts  用于拆分的标题文本（按文档先后顺序）
     */
    public static void splitByHeadingText(String srcPath, String outputDir,
                                          String[] headingTexts) throws Exception {
        Document doc = new Document(srcPath);
        File dir = new File(outputDir);
        if (!dir.exists()) dir.mkdirs();

        // 1. 找出所有匹配的标题段落（按文档顺序）
        List<Paragraph> headingParas = new ArrayList<>();
        NodeCollection<Paragraph> allParas = doc.getChildNodes(NodeType.PARAGRAPH, true);

        for (Paragraph para : allParas) {
            String text = para.toString(SaveFormat.TEXT).trim();
            for (String hText : headingTexts) {
                if (text.equals(hText)) {     // 精确匹配，如需包含关系可改用 contains
                    headingParas.add(para);
                    break;
                }
            }
        }

        if (headingParas.isEmpty()) {
            System.out.println("未找到任何匹配的标题文本，无法拆分。");
            return;
        }

        // 2. 按标题位置拆分并保存
        int partIndex = 1;
        for (int i = 0; i < headingParas.size(); i++) {
            Paragraph start = headingParas.get(i);
            Paragraph end = (i + 1 < headingParas.size()) ? headingParas.get(i + 1) : null;

            Document newDoc = extractContent(doc, start, end);

            // 用标题文本作为文件名，并清理非法字符
            String titleText = start.toString(SaveFormat.TEXT).trim();
            titleText = titleText.replaceAll("[\\\\/:*?\"<>|]", "").trim();
            String fileName = titleText.isEmpty() ? "part_" + partIndex : titleText;
            String outputPath = outputDir + File.separator + fileName + ".docx";

            newDoc.save(outputPath);
            System.out.println("已生成: " + outputPath);
            partIndex++;
        }

        System.out.println("拆分完成，共生成 " + (partIndex - 1) + " 个文档。");
    }

    /**
     * 提取从 start 节点（包含）到 end 节点（不包含）之间的内容，生成独立文档
     */
    private static Document extractContent(Document srcDoc, Paragraph start, Paragraph end) throws Exception {
        // 1. 创建一个全新的空白文档（默认 A4 纵向，无任何内容）
        Document dstDoc = new Document();
        Section dstSection = dstDoc.getFirstSection(); // 新文档一定有节，不会为 null

        // 2. 将源文档中 [start, end) 范围的节点全部深拷贝过来
        Node currNode = start;
        while (currNode != null && currNode != end) {
            Node nextNode = currNode.getNextSibling(); // 先取下一个节点
            Node imported = dstDoc.importNode(currNode, true);
            dstSection.getBody().appendChild(imported);
            currNode = nextNode;
        }

        // 3. 兜底：确保至少有一个段落，避免空文档
        if (dstSection.getBody().getChildNodes().getCount() == 0) {
            dstSection.getBody().appendChild(new Paragraph(dstDoc));
        }

        return dstDoc;
    }

}
