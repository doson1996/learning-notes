package com.ds.basic.poi;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;

/**
 * @author ds
 * @date 2025/5/14
 * @description
 */
public class WordToHtml {
    public static void main(String[] args) throws Exception {
        // 加载 Word 文档
        Document doc = new Document("output.docx");

        // 使用 ByteArrayOutputStream 保存为 HTML 内容
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        doc.save(stream, SaveFormat.HTML);

        // 获取 HTML 字符串
        String htmlContent = new String(stream.toByteArray(), StandardCharsets.UTF_8);

        // 输出或返回 htmlContent
        System.out.println(htmlContent);
    }
}
