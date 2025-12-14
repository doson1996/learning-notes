package com.ds.office.pdf;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;

/**
 * @author ds
 * @date 2025/3/25
 * @description 
 */
public class PdfDemo {
    public static void main(String[] args) {
        // 定义PDF文件的路径
        String dest = "/Users/ds/IdeaProjects/learning-notes/office/src/main/resources/output.pdf";

        // 创建一个File对象
        File file = new File(dest);
        file.getParentFile().mkdirs();

        try {
            // 初始化PdfWriter
            PdfWriter writer = new PdfWriter(dest);

            // 初始化PdfDocument
            PdfDocument pdf = new PdfDocument(writer);

            // 初始化Document
            Document document = new Document(pdf);

            // 添加封面内容
            addCoverPage(document);

            // 添加其他内容到PDF
            document.add(new Paragraph("Hello World!"));

            // 关闭文档
            document.close();

            System.out.println("PDF created successfully.");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void addCoverPage(Document document) {
        try {
            // 使用中文字体
            PdfFont font = PdfFontFactory.createFont("STSongStd-Light", PdfEncodings.IDENTITY_H, true);

            // 添加标题
            Paragraph title = new Paragraph(new Text("Title").setFont(font))
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(24);
            document.add(title);



            // 添加副标题
            Paragraph subtitle = new Paragraph(new Text("这是一个示例报告").setFont(font))
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(18);
            document.add(subtitle);

            // 添加日期
            Paragraph date = new Paragraph(new Text("日期: " + LocalDate.now()).setFont(font))
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontSize(14);
            document.add(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}