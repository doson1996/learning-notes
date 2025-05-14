
package com.ds.basic.poi;

import java.nio.file.Files;
import java.nio.file.Path;

import com.aspose.words.BreakType;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.ParagraphAlignment;
import com.aspose.words.Underline;

public class WordCoverGenerator {
    public static void main(String[] args) throws Exception {
        // 创建一个新的文档
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);

        // 添加主标题
        builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
        builder.getFont().setSize(18);
        builder.getFont().setBold(false);
        builder.getFont().setName("方正小标宋_GBK");
        builder.writeln("重庆银行大中公司授信业务调查报告");

        // 添加副标题
        builder.getFont().setSize(16);
        builder.writeln("(2024年版)");

        // 插入空行
        builder.insertBreak(BreakType.LINE_BREAK);
        builder.insertBreak(BreakType.LINE_BREAK);
        builder.insertBreak(BreakType.LINE_BREAK);
        builder.insertBreak(BreakType.LINE_BREAK);
        builder.insertBreak(BreakType.LINE_BREAK);
        builder.insertBreak(BreakType.LINE_BREAK);

        builder.getParagraphFormat().setAlignment(ParagraphAlignment.LEFT);
        String kg = "         ";
        // 客户名称
        addUnderlinedRow(builder, kg + "客户名称：", "重庆测试技术有限公司");

        // 申报机构
        addUnderlinedRow(builder, kg + "申报机构：", "延安分行");

        // 所属集团
        addUnderlinedRow(builder, kg + "所属集团：", "重庆测试（集团）有限责任公司");

        // 申报日期
        addUnderlinedRow(builder, kg + "申报日期：", "2025.05.13");

        // 联系人员
        addUnderlinedRow(builder, kg + "联系人员：", "张三");

        // 联系方式
        addUnderlinedRow(builder, kg + "联系方式：", "13012345678");

        // 保存到临时文件
        doc.save("output/Cover.docx");


    }

    // 封装添加下划线行的方法，提升可读性和复用性
    private static void addUnderlinedRow(DocumentBuilder builder, String label, String value) throws Exception {


        // 设置标签样式
        builder.getFont().setName("方正仿宋_GBK");
        builder.getFont().setSize(14);
        builder.write(label);

        int maxLength = 14 + 20;

        int valueLength = value.length();

        // 定义每行下划线的固定长度（例如 50 个字符）
        int underlineLength = maxLength - valueLength;

        // 计算 value 在下划线中的起始位置，使其居中
        int paddingLength = (underlineLength - valueLength) / 2;

        // 插入空格和下划线
        builder.getFont().setUnderline(Underline.SINGLE);
        for (int i = 0; i < underlineLength; i++) {
            if (i >= paddingLength && i < paddingLength + valueLength) {
                // 在指定位置写入 value
                builder.write(String.valueOf(value.charAt(i - paddingLength)));
            } else {
                builder.write("  ");
            }
        }
        builder.getFont().setUnderline(Underline.NONE);

        // 插入空格和下划线
        builder.insertBreak(BreakType.LINE_BREAK);
        builder.getFont().setName("方正仿宋_GBK");
        builder.getFont().setSize(14);

        builder.getFont().setUnderline(Underline.NONE);

        // 插入空行
        builder.insertBreak(BreakType.LINE_BREAK);
    }
}