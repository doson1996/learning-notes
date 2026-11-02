package com.ds.basic.poi.cwfx;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;

import com.aspose.words.Document;
import com.aspose.words.HtmlSaveOptions;
import com.aspose.words.SaveFormat;

/**
 * @author ds
 * @date 2026/10/28
 * @description
 */
public class Word2Html {
    public static void main(String[] args) throws Exception {
        File file = new File("D:\\haizhi\\doc\\2025\\03.尽调\\数字分析助手\\报告模板\\output\\五、申请人财务情况调查.docx");
        // 加载 Word 文档
        Document doc = new Document(Files.newInputStream(file.toPath()));

        // 创建 HtmlSaveOptions 并设置图片存储目录
        HtmlSaveOptions saveOptions = new HtmlSaveOptions(SaveFormat.HTML);
        saveOptions.setImagesFolder("D://docx/images"); // 正确设置图片目录
        saveOptions.setExportDocumentProperties(false);
        // 使用 ByteArrayOutputStream 保存为 HTML 内容
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        doc.save(stream, saveOptions);

        String htmlContent = new String(stream.toByteArray(), StandardCharsets.UTF_8);

        // 提取表格数据
//        List<Map<String, String>> data = RegexTableExtractor.extract(htmlContent);
//        List<Map<String, String>> data = JdkHtmlTableExtractor.extract(htmlContent);
//        for (Map<String, String> row : data) {
//            System.out.println("row = " + row);
//        }

        // 提取非表格数据
        String extract = NonTableTextExtractor.extract(htmlContent);
        System.out.println("extract = " + extract);

    }
}
