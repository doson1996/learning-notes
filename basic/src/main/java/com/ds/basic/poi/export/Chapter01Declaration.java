package com.ds.basic.poi.export;

import com.aspose.words.BreakType;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.ParagraphAlignment;

/**
 * 声明
 */
public class Chapter01Declaration {
    public static void main(String[] args) throws Exception {
        // 创建一个新的文档
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);
        builder.getParagraphFormat().setLineSpacing(18);
        // 设置页面边距
        builder.getPageSetup().setLeftMargin(72); // 1 inch
        builder.getPageSetup().setRightMargin(72); // 1 inch
        builder.getPageSetup().setTopMargin(72); // 1 inch
        builder.getPageSetup().setBottomMargin(72); // 1 inch

        // 添加标题
        builder.getFont().setSize(16);  //三号
        builder.getFont().setName("方正小标宋_GBK");
        builder.writeln("本机构在此声明:");

        // 添加正文
        builder.getFont().setName("仿宋_GB2312");
        builder.getFont().setSize(16);  // 三号
        builder.write("    ");
        builder.write("本调查报告作为授信重要决策材料，代表机构贷前调查的尽职调查结果。本机构根据重庆银行股份有限公司相关制度和流程，根据借款人、担保人、其他第三方提供的材料并核对了相关原件，复印材料加盖了企业公章，结合机构调查人搜集的其他材料，经机构审慎调查、核实、分析和整理后完成的。报告全面反映了客户的主要信息，机构对报告内容的真实性、准确性、完整性及所作判断的合理性负责，并同意本次授信业务申报方案。");
        builder.writeln();
        builder.writeln();

        // 添加签名行
        builder.write("主办调查人1（签名）：             协办调查人2（签名）：");
        // 插入空行
        builder.writeln();
        builder.write("                              申报机构盖章（加盖骑缝章）");
        // 插入空行
        builder.writeln();
        builder.write("                                      年    月    日");

        // 保存文档
        doc.save("output/declaration.docx");
    }
}