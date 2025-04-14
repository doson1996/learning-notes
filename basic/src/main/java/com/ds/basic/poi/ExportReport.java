package com.ds.basic.poi;

import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.ImportFormatMode;
import com.aspose.words.NodeCollection;
import com.aspose.words.NodeType;
import com.aspose.words.Paragraph;
import com.aspose.words.ParagraphAlignment;
import com.aspose.words.ParagraphCollection;
import com.aspose.words.StyleIdentifier;

/**
 * @author ds
 * @date 2025/4/14
 * @description 导出报告示例
 */
public class ExportReport {

    public static final int SPACE_BEFORE = 20;
    
    public static final int SPACE_AFTER = 20;
    
    public static void main(String[] args) throws Exception {
        String file1 = "D://docx//output//采矿业研究（2025_1.docx";
        String file2 = "D://docx//output//城市交通行业研究（2025_1.docx";
        String file3 = "D://docx//output/a.docx";

        // 创建一个新的文档
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);

        // 添加标题
        builder.getParagraphFormat().setStyleIdentifier(StyleIdentifier.HEADING_1);
        builder.getParagraphFormat().setSpaceAfter(SPACE_BEFORE);
        builder.getParagraphFormat().setSpaceBefore(0);
        builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER); // 设置居中对齐

        builder.getFont().setBold(true); // 设置加粗
        builder.writeln("重庆银行小微公司授信业务调查报告");
        builder.getFont().setBold(false); // 重置加粗

        // 添加段落
        builder.getParagraphFormat().setStyleIdentifier(StyleIdentifier.NORMAL);
        builder.getParagraphFormat().setAlignment(ParagraphAlignment.JUSTIFY);
        // 设置首行缩进，单位是磅（point）， 0.85厘米 = 24.09磅
        builder.getParagraphFormat().setFirstLineIndent(24.09);
        builder.writeln("本调查报告作为授信重要决策材料，代表机构贷前调查的尽职调查结果。本机构根据重庆银行股份有限公司相关制度和流程，根据借款人、担保人、其他第三方提供的材料并核对了相关原件，复印材料加盖了企业公章，结合机构调查人搜集的其他材料，经机构审慎调查、核实、分析和整理后完成的。报告全面反映了客户的主要信息，机构对报告内容的真实性、准确性、完整性及所作判断的合理性负责，并同意本次授信业务申报方案。");

        // 重置首行缩进为0，如果需要
        builder.getParagraphFormat().setFirstLineIndent(0.0);

        // 添加标题
        builder.getParagraphFormat().setStyleIdentifier(StyleIdentifier.HEADING_1);
        builder.getParagraphFormat().setSpaceAfter(SPACE_BEFORE);
        builder.getParagraphFormat().setSpaceBefore(SPACE_AFTER);
        builder.writeln("一、行业分析");

        // 加载第一个文档
        Document doc1 = new Document(file1);
        // 清理源文档中的分页符和段落
//        cleanDocument(doc1);
        // 将第一个文档的内容插入到文档行业分析模块中
//        builder.insertDocument(doc1, ImportFormatMode.KEEP_SOURCE_FORMATTING);
        builder.insertDocument(doc1, ImportFormatMode.USE_DESTINATION_STYLES);

        builder.getParagraphFormat().setStyleIdentifier(StyleIdentifier.HEADING_1);
        builder.getParagraphFormat().setSpaceAfter(SPACE_BEFORE);
        builder.getParagraphFormat().setSpaceBefore(SPACE_AFTER);
        builder.writeln("二、财务分析");

        // 加载第二个文档
        Document doc2 = new Document(file2);
        // 将第一个文档的内容插入到文档行业分析模块中
        builder.insertDocument(doc2, ImportFormatMode.USE_DESTINATION_STYLES);
        // 设置段落间距为零
        builder.getParagraphFormat().setSpaceAfter(0);
        builder.getParagraphFormat().setSpaceBefore(0);


        // 保存文档
        doc.save(file3);

    }


    private static void cleanDocument(Document doc) {
        // 获取所有段落节点
        NodeCollection paragraphs = doc.getChildNodes(NodeType.PARAGRAPH, true);

        // 清理文档开头的空段落
        while (paragraphs.getCount() > 0 && isParagraphEmpty((Paragraph) paragraphs.get(0))) {
            paragraphs.remove(paragraphs.get(0));
        }

        // 清理文档末尾的空段落
        while (paragraphs.getCount() > 0 && isParagraphEmpty((Paragraph) paragraphs.get(paragraphs.getCount() - 1))) {
            paragraphs.remove(paragraphs.get(paragraphs.getCount() - 1));
        }
    }

    // 辅助方法：判断段落是否为空
    private static boolean isParagraphEmpty(Paragraph paragraph) {
        return paragraph.getText().trim().isEmpty();
    }

    // 辅助方法：检查段落是否包含分页符
    private static boolean containsPageBreak(Paragraph paragraph) {
        for (com.aspose.words.Run run : paragraph.getRuns()) {
            if (run.getText().contains("\f")) {
                return true;
            }
        }
        return false;
    }

    private static void adjustParagraphStyles(Document doc) {
        // 获取所有段落节点
        ParagraphCollection paragraphs = (ParagraphCollection) doc.getChildNodes(NodeType.PARAGRAPH, true);

        // 遍历段落集合并调整样式
        for (int i = 0; i < paragraphs.getCount(); i++) {
            com.aspose.words.Paragraph paragraph = paragraphs.get(i);
            paragraph.getParagraphFormat().setSpaceAfter(0);
            paragraph.getParagraphFormat().setSpaceBefore(0);
        }
    }
}
