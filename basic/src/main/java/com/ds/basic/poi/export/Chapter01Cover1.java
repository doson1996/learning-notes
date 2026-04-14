package com.ds.basic.poi.export;

import static com.aspose.words.RevisionTextEffect.UNDERLINE;

import com.aspose.words.*;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

public class Chapter01Cover1 {

    private static java.util.List<Document> documents = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        // 确保输出目录存在
        new File("output").mkdirs();

        // 生成单个文档（原 main 逻辑）
        Document doc = createCoverDocument("数智尽调-张三-2687",
                "重庆测试技术有限公司", "延安分行", "重庆测试（集团）有限责任公司",
                "2025.05.13", "张三", "13012345678");
        doc.save("output/Cover.docx");
        documents.add(doc);

        // 批量生成多个文档
        generateMultipleDocuments();
    }

    public static void generateMultipleDocuments() throws Exception {
        String[] watermarks = {"数智尽调-张三-2687", "数智尽调-李四-2688", "数智尽调-王五-2689"};
        String[] customers = {"重庆测试技术有限公司", "西安科技公司", "成都数据集团"};
        String[] branches = {"延安分行", "西安分行", "成都分行"};
        String[] groups = {"重庆测试（集团）有限责任公司", "西安控股集团", "成都数智集团"};
        String[] dates = {"2025.05.13", "2025.05.14", "2025.05.15"};
        String[] contacts = {"张三", "李四", "王五"};
        String[] phones = {"13012345678", "13812345678", "13912345678"};

        for (int i = 0; i < watermarks.length; i++) {
            Document doc = createCoverDocument(watermarks[i], customers[i], branches[i],
                    groups[i], dates[i], contacts[i], phones[i]);
            doc.save("output/Cover" + (i+1) + ".docx");
            documents.add(doc);
        }
    }

    private static Document createCoverDocument(String watermarkText, String customer, String branch,
                                                String group, String date, String contact, String phone) throws Exception {
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);

        // 1. 添加页眉水印
        addWatermark(builder, watermarkText);

        // 2. 返回正文
        builder.moveToDocumentStart();
        builder.moveToSection(0);

        // 3. 主标题
        builder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
        builder.getFont().setSize(18);
        builder.getFont().setBold(false);
        builder.getFont().setName("方正小标宋_GBK");
        builder.writeln("xx银行大中公司授信业务调查报告");

        // 4. 副标题
        builder.getFont().setSize(16);
        builder.writeln("(2024年版)");

        // 5. 空行
        for (int i = 0; i < 6; i++) builder.insertBreak(BreakType.LINE_BREAK);

        // 6. 信息行（使用表格）
        double labelWidth = millimetersToPoints(35);
        double valueWidth = millimetersToPoints(80);
        addUnderlinedRowWithTable(builder, "客户名称：", customer, labelWidth, valueWidth);
        addUnderlinedRowWithTable(builder, "申报机构：", branch, labelWidth, valueWidth);
        addUnderlinedRowWithTable(builder, "所属集团：", group, labelWidth, valueWidth);
        addUnderlinedRowWithTable(builder, "申报日期：", date, labelWidth, valueWidth);
        addUnderlinedRowWithTable(builder, "联系人员：", contact, labelWidth, valueWidth);
        addUnderlinedRowWithTable(builder, "联系方式：", phone, labelWidth, valueWidth);

        for (int i = 0; i < 6; i++) builder.insertBreak(BreakType.LINE_BREAK);

        return doc;
    }

    /**
     * 将毫米转换为点（Aspose.Words 单位）
     * 1 毫米 = 2.83464567 点
     */
    private static double millimetersToPoints(double millimeters) {
        return millimeters * 2.83464567;
    }

    /**
     * 在页眉右侧添加灰色水印（不锁定文档）
     */
    private static void addWatermark(DocumentBuilder builder, String text) throws Exception {
        builder.moveToHeaderFooter(HeaderFooterType.HEADER_PRIMARY);
        builder.getParagraphFormat().setAlignment(ParagraphAlignment.RIGHT);
        builder.getFont().setName("Arial");
        builder.getFont().setSize(14);
        builder.getFont().setColor(Color.LIGHT_GRAY);
        builder.writeln(text);
        // 注意：不要调用 protect()，否则后续无法编辑正文
    }

    /**
     * 使用表格添加信息行，保证左边对齐（标签右对齐）且下划线长度一致
     * @param builder    DocumentBuilder
     * @param label      标签文字（如“客户名称：”）
     * @param value      值文字
     * @param labelWidth 标签列宽度（单位：点，推荐 80 点）
     * @param valueWidth 值列宽度（单位：点，推荐 200 点）
     */
    private static void addUnderlinedRowWithTable(DocumentBuilder builder, String label, String value,
                                                  double labelWidth, double valueWidth) throws Exception {
        // 开始表格
        builder.startTable();

        // 设置表格无边框
        builder.getCellFormat().getBorders().setLineStyle(LineStyle.NONE);

        // 第一列：标签（右对齐，固定宽度）
        builder.insertCell();
        builder.getCellFormat().setWidth(labelWidth);
        builder.getParagraphFormat().setAlignment(ParagraphAlignment.RIGHT);
        builder.getFont().setName("方正仿宋_GBK");
        builder.getFont().setSize(14);
        builder.getFont().setUnderline(Underline.NONE);
        builder.write(label);
        builder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);

        // 第二列：带下划线的值（左对齐，固定宽度，底部边框作为下划线）
        builder.insertCell();
        builder.getCellFormat().setWidth(valueWidth);
        builder.getParagraphFormat().setAlignment(ParagraphAlignment.LEFT);
        builder.getFont().setUnderline(Underline.NONE);
        builder.write(value);
        // 设置单元格底部边框（下划线效果）
        builder.getCellFormat().getBorders().getByBorderType(BorderType.BOTTOM).setLineStyle(LineStyle.SINGLE);
        builder.getCellFormat().getBorders().getByBorderType(BorderType.BOTTOM).setLineWidth(0.75);

        // 结束行
        builder.endRow();
        builder.endTable();

        // 表格后插入一个空行（与后续内容分隔）
        builder.insertBreak(BreakType.LINE_BREAK);
    }

    /**
     * 计算字符串显示宽度（中文算2，英文数字算1）
     */
    private static int getDisplayWidth(String text) {
        int width = 0;
        for (char c : text.toCharArray()) {
            width += isChineseChar(c) ? 2 : 1;
        }
        return width;
    }

    private static boolean isChineseChar(char c) {
        Character.UnicodeBlock block = Character.UnicodeBlock.of(c);
        return block == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || block == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || block == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || block == Character.UnicodeBlock.CJK_RADICALS_SUPPLEMENT
                || block == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION;
    }
}