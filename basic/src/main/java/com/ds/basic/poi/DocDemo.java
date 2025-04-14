package com.ds.basic.poi;

import com.aspose.words.BreakType;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.ImportFormatMode;
import com.aspose.words.StyleIdentifier;

/**
 * @author ds
 * @date 2025/4/14
 * @description
 */
public class DocDemo {
    public static void main(String[] args) throws Exception {
        report();
        // 创建一个文档
//        createDocument();
        // 插入分页符
//        insertPageBreak();
    }

    public static void report() throws Exception {
        String file1 = "D://docx//output//采矿业研究（2025_1.docx";
        String file2 = "D://docx//output//城市交通行业研究（2025_1.docx";
        String file3 = "D://docx//output/a.docx";

        // 创建一个新的文档
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);

        // 添加标题
        builder.getParagraphFormat().setStyleIdentifier(StyleIdentifier.HEADING_1);
        builder.getParagraphFormat().setSpaceAfter(10);
        builder.getParagraphFormat().setSpaceBefore(10);
        builder.writeln("一、行业分析");


        // 加载第一个文档
        Document doc1 = new Document(file1);
        // 清理源文档中的分页符和段落
        // 将第一个文档的内容插入到文档行业分析模块中
        builder.insertDocument(doc1, ImportFormatMode.KEEP_SOURCE_FORMATTING);

        builder.getParagraphFormat().setStyleIdentifier(StyleIdentifier.HEADING_1);
        builder.getParagraphFormat().setSpaceAfter(10);
        builder.getParagraphFormat().setSpaceBefore(10);
        builder.writeln("二、财务分析");

        // 加载第二个文档
        Document doc2 = new Document(file2);
        // 将第一个文档的内容插入到文档行业分析模块中
        builder.insertDocument(doc2, ImportFormatMode.KEEP_SOURCE_FORMATTING);
        // 设置段落间距为零
        builder.getParagraphFormat().setSpaceAfter(0);
        builder.getParagraphFormat().setSpaceBefore(0);


        // 添加段落
//        builder.getParagraphFormat().setStyleIdentifier(StyleIdentifier.NORMAL);
//        builder.getParagraphFormat().setAlignment(ParagraphAlignment.JUSTIFY);
//        builder.writeln("这是第一段文本。这是第一段文本。这是第一段文本。这是第一段文本。");

        // 保存文档
        doc.save(file3);
    }



    /**
     * 创建一个文档
     *
     * @throws Exception
     */
    public static void createDocument() throws Exception {
        // 创建一个新的文档
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);

        // 添加一些文本
        builder.writeln("这是第一段文本。");
        builder.writeln("这是第二段文本。");

        // 保存文档
        doc.save("D://docx//output//b.docx");
    }

    /**
     * 插入分页符
     *
     * @throws Exception
     */
    public static void insertPageBreak() throws Exception {
        // 创建一个新的文档
        Document doc = new Document();
        DocumentBuilder builder = new DocumentBuilder(doc);

        // 添加一些文本
        builder.writeln("这是第一段文本。");

        // 插入分页符
        builder.insertBreak(BreakType.PAGE_BREAK);

        // 添加更多文本
        builder.writeln("这是第二段文本。");

        // 保存文档
        doc.save("D://docx//output//b.docx");
    }
}
