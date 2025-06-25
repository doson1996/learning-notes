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
        String htmlContent = "";
        builder.insertHtml(htmlContent);

        // 保存为 .docx 文件
        doc.save("output.docx", SaveFormat.DOCX);

        System.out.println("HTML 转换为 Word 成功！");


    }
}
