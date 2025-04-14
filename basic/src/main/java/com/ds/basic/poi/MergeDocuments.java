package com.ds.basic.poi;

import com.aspose.words.BreakType;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.ImportFormatMode;

/**
 * @author ds
 * @date 2025/4/14
 * @description
 */
public class MergeDocuments {
    public static void main(String[] args) throws Exception {
        String file1 = "D://docx//output//采矿业研究（2025_1.docx";
        String file2 = "D://docx//output//城市交通行业研究（2025_1.docx";
        String file3 = "D://docx//output/a.docx";

        // 加载第一个文档
        Document dstDoc = new Document(file1);
        // 创建一个 DocumentBuilder 对象
        DocumentBuilder builder = new DocumentBuilder(dstDoc);
        // 将光标移动到文档末尾
        builder.moveToDocumentEnd();
        // 如果需要，插入分页符 （中间会有空白页）
//        builder.insertBreak(BreakType.PAGE_BREAK);
        // 加载第二个文档
        Document srcDoc = new Document(file2);
        // 将第二个文档的内容插入到第一个文档中
        dstDoc.appendDocument(srcDoc, ImportFormatMode.KEEP_SOURCE_FORMATTING);
        // 保存合并后的文档
        dstDoc.save(file3);
    }
}
