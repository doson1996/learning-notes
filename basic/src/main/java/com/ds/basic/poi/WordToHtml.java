package com.ds.basic.poi;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import com.aspose.words.Document;
import com.aspose.words.HtmlSaveOptions;
import com.aspose.words.SaveFormat;

/**
 * @author ds
 * @date 2025/5/14
 * @description
 */
public class WordToHtml {
    public static void main(String[] args) throws Exception {
        File file = new File("D:\\书籍\\数字分析助手\\尽调-财务状况综合分析（大中）0410.docx");
        // 加载 Word 文档
        Document doc = new Document(Files.newInputStream(file.toPath()));

        // 创建 HtmlSaveOptions 并设置图片存储目录
        HtmlSaveOptions saveOptions = new HtmlSaveOptions(SaveFormat.HTML);
        saveOptions.setImagesFolder("D://docx/images"); // 正确设置图片目录
        saveOptions.setExportDocumentProperties(false);
        // 使用 ByteArrayOutputStream 保存为 HTML 内容
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        doc.save(stream, saveOptions);

        // 获取 HTML 字符串
        String htmlContent = new String(stream.toByteArray(), StandardCharsets.UTF_8);

        htmlContent = htmlContent
                .replaceAll("</html>", "")
                .replaceAll("<html>", "")
                .replaceAll("<head>.*?</head>", "")
                .replaceAll("</body>", "")
                .replaceAll("<body.*?>", "")
                .replaceAll("</div>", "</p>")
                .replaceAll("<div>", "<p>")
                .replaceAll("<div.*?>", "")
                .replaceAll("<a.*?>", "")
                .replaceAll("</a>", "");

        // 输出或返回 htmlContent
        System.out.println(htmlContent);
    }
}
