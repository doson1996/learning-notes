package com.ds.basic.poi;

import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.SaveFormat;

/**
 * @author ds
 * @date 2025/5/14
 * @description
 */
public class HtmlToWord {
    public static void main(String[] args) throws Exception {
        // 创建一个空的 Word 文档
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);

        // 插入 HTML 内容
        String htmlContent = "<table style=\"width: auto;\"><tbody><tr><th colSpan=\"1\" rowSpan=\"1\" width=\"auto\">列1</th><th colSpan=\"1\" rowSpan=\"1\" width=\"auto\">列2</th><th colSpan=\"1\" rowSpan=\"1\" width=\"auto\">列3</th><th colSpan=\"1\" rowSpan=\"1\" width=\"auto\">列4</th><th colSpan=\"1\" rowSpan=\"1\" width=\"auto\">列5</th></tr><tr><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">1</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">1</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">1</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">1</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">1</td></tr><tr><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">1</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">1</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">1</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">1</td><td colSpan=\"1\" rowSpan=\"1\" width=\"auto\">1</td></tr></tbody></table><p><strong>dsadsds</strong></p ><p><u>dsadsad</u></p ><p><u><em>fdsfsd</em></u></p ><p><span style=\"font-size: 22px;\"><u><em>dsadsadsad</em></u></span></p ><p style=\"line-height: 3;\"><span style=\"font-size: 22px;\"><u><em>dsadsad</em></u></span></p ><p style=\"line-height: 1;\"><span style=\"font-size: 22px; font-family: 仿宋;\">测试</span><span style=\"color: rgb(255, 77, 79); font-size: 22px; font-family: 仿宋;\">的撒的撒</span></p ><p style=\"text-align: center; line-height: 1;\"><span style=\"color: rgb(255, 77, 79); font-size: 22px; font-family: 仿宋;\">居中</span></p >";
        builder.insertHtml(htmlContent);

        // 保存为 .docx 文件
        doc.save("output.docx", SaveFormat.DOCX);

        System.out.println("HTML 转换为 Word 成功！");


    }
}
