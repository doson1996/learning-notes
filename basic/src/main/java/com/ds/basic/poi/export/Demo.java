package com.ds.basic.poi.export;

import java.util.ArrayList;
import java.util.List;

import com.aspose.words.BreakType;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.HeaderFooterType;
import com.aspose.words.ParagraphAlignment;
import com.aspose.words.ProtectionType;
import com.aspose.words.Range;
import com.aspose.words.Underline;

/**
 * @author ds
 * @date 2026/2/10
 * @description 页眉水印
 */
public class Demo {

    public static void main(String[] args) {
        try {
            generateDocumentWithSameWatermark();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateDocumentWithSameWatermark() throws Exception {
        // 创建新文档
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);

        // 设置统一水印（仅需在第一页设置）
        builder.moveToHeaderFooter(HeaderFooterType.HEADER_PRIMARY);
        addWatermark(builder, "统一水印"); // 插入水印

        // 返回正文
        builder.moveToSection(0);
        builder.moveToDocumentStart();

        // 第一页内容
        builder.writeln("第一页内容");
        builder.insertBreak(BreakType.PAGE_BREAK); // 分页符

        // 第二页内容
        builder.writeln("第二页内容");
        builder.insertBreak(BreakType.PAGE_BREAK); // 分页符

        // 第三页内容
        builder.writeln("第三页内容");

        // 保存文档
        doc.save("output/SameWatermarkDocument.docx");
    }

    private static void addWatermark(DocumentBuilder builder, String text) throws Exception {
        // 设置水印样式
        builder.getFont().setName("Arial");
        builder.getFont().setSize(14);
        builder.getFont().setColor(java.awt.Color.LIGHT_GRAY);

        // 插入水印文本
        builder.getParagraphFormat().setAlignment(ParagraphAlignment.RIGHT);
        builder.writeln(text);

        // 锁定页眉以防止编辑
        Range headerRange = builder.getCurrentSection().getHeadersFooters()
                .getByHeaderFooterType(HeaderFooterType.HEADER_PRIMARY).getRange();
        headerRange.toDocument().protect(ProtectionType.READ_ONLY, "password");
    }

}
