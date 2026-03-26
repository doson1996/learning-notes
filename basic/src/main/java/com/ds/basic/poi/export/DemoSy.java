package com.ds.basic.poi.export;

import java.awt.Color;

import com.aspose.words.BreakType;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.HeaderFooterType;
import com.aspose.words.HorizontalAlignment;
import com.aspose.words.Paragraph;
import com.aspose.words.ParagraphAlignment;
import com.aspose.words.ProtectionType;
import com.aspose.words.Range;
import com.aspose.words.RelativeHorizontalPosition;
import com.aspose.words.RelativeVerticalPosition;
import com.aspose.words.Run;
import com.aspose.words.Shape;
import com.aspose.words.ShapeType;
import com.aspose.words.VerticalAlignment;
import com.aspose.words.WrapType;

/**
 * @author ds
 * @date 2026/2/10
 * @description
 */
public class DemoSy {
    public static void main(String[] args) throws Exception {
        generateDocumentWithSameWatermark();
    }

    public static void generateDocumentWithSameWatermark() throws Exception {
        // 创建新文档
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);

        // 设置统一水印（仅需在第一页设置）
        builder.moveToHeaderFooter(HeaderFooterType.HEADER_PRIMARY);
        addWpsStyleTextWatermark(builder, "统一水印"); // 插入水印

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

    private static void addWpsStyleTextWatermark(DocumentBuilder builder, String text) throws Exception {
        // 移动到页眉区域
        builder.moveToHeaderFooter(HeaderFooterType.HEADER_PRIMARY);

        // 创建一个文本框形状作为水印
        Shape shape = new Shape(builder.getDocument(), ShapeType.TEXT_BOX);
        shape.setWidth(100); // 设置宽度
        shape.setHeight(100); // 设置高度
        shape.setRelativeHorizontalPosition(RelativeHorizontalPosition.PAGE);
        shape.setRelativeVerticalPosition(RelativeVerticalPosition.PAGE);
        shape.setWrapType(WrapType.NONE);
        shape.setHorizontalAlignment(HorizontalAlignment.CENTER);
        shape.setVerticalAlignment(VerticalAlignment.CENTER);

        // 设置文本框内容
        Paragraph paragraph = new Paragraph(builder.getDocument());
        Run run = new Run(builder.getDocument(), text);
        run.getFont().setName("Arial");
        run.getFont().setSize(9);
        run.getFont().setColor(new Color(200, 200, 200)); // 淡灰色
        paragraph.appendChild(run);
        shape.appendChild(paragraph);

//         插入形状到文档
        builder.insertNode(shape);

//         设置旋转角度
        shape.setRotation(-45); // 逆时针旋转45度

        // 锁定页眉防止编辑
        Range headerRange = builder.getCurrentSection().getHeadersFooters()
                .getByHeaderFooterType(HeaderFooterType.HEADER_PRIMARY).getRange();
        headerRange.toDocument().protect(ProtectionType.READ_ONLY, "password");
    }

    private static void addWpsStyleImageWatermark(DocumentBuilder builder, String imagePath) throws Exception {
        // 移动到页眉区域
        builder.moveToHeaderFooter(HeaderFooterType.HEADER_PRIMARY);

        // 插入图片水印
        builder.insertImage(imagePath, RelativeHorizontalPosition.PAGE, 0,
                RelativeVerticalPosition.PAGE, 0, 500, 500, WrapType.NONE);

        // 设置图片透明度
        Shape shape = (Shape) builder.getCurrentNode().getPreviousSibling();
        shape.getImageData().setBrightness(0.7); // 降低亮度模拟透明效果

        // 锁定页眉防止编辑
        Range headerRange = builder.getCurrentSection().getHeadersFooters()
                .getByHeaderFooterType(HeaderFooterType.HEADER_PRIMARY).getRange();
        headerRange.toDocument().protect(ProtectionType.READ_ONLY, "password");
    }

}
