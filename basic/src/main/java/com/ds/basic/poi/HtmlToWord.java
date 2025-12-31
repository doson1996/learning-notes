package com.ds.basic.poi;

import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.ParagraphAlignment;
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
        builder.getParagraphFormat().setAlignment(ParagraphAlignment.JUSTIFY);
        // 插入 HTML 内容
        String htmlContent = "<p>123123</p><table style=\"width: 100%;border-left: 1px solid #999999;border-top: 1px solid #999999\">\n" +
                "\t<tbody>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td style=\"text-align: left;width: 20%;line-height: 25px;border-right: 1px solid #999999;border-bottom: 1px solid #999999\">表头1</td>\n" +
                "\t\t\t<td style=\"text-align: left;width: 20%;line-height: 25px;border-right: 1px solid #999999;border-bottom: 1px solid #999999\">表头2</td>\n" +
                "\t\t\t<td style=\"text-align: left;width: 20%;line-height: 25px;border-right: 1px solid #999999;border-bottom: 1px solid #999999\">表头3</td>\n" +
                "\t\t\t<td style=\"text-align: left;width: 20%;line-height: 25px;border-right: 1px solid #999999;border-bottom: 1px solid #999999\">表头4</td>\n" +
                "\t\t\t<td style=\"text-align: left;width: 20%;line-height: 25px;border-right: 1px solid #999999;border-bottom: 1px solid #999999\">表头5</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td style=\"text-align: left;width: 20%;line-height: 25px;border-right: 1px solid #999999;border-bottom: 1px solid #999999\">AA</td>\n" +
                "\t\t\t<td style=\"text-align: left;width: 20%;line-height: 25px;border-right: 1px solid #999999;border-bottom: 1px solid #999999\">BB</td>\n" +
                "\t\t\t<td style=\"text-align: left;width: 20%;line-height: 25px;border-right: 1px solid #999999;border-bottom: 1px solid #999999\">CC</td>\n" +
                "\t\t\t<td style=\"text-align: left;width: 20%;line-height: 25px;border-right: 1px solid #999999;border-bottom: 1px solid #999999\">DD</td>\n" +
                "\t\t\t<td style=\"text-align: left;width: 20%;line-height: 25px;border-right: 1px solid #999999;border-bottom: 1px solid #999999\">EE</td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td style=\"text-align: left;width: 20%;line-height: 25px;border-right: 1px solid #999999;border-bottom: 1px solid #999999\">FF</td>\n" +
                "\t\t\t<td style=\"text-align: left;width: 20%;line-height: 25px;border-right: 1px solid #999999;border-bottom: 1px solid #999999\">GG</td>\n" +
                "\t\t\t<td style=\"text-align: left;width: 20%;line-height: 25px;border-right: 1px solid #999999;border-bottom: 1px solid #999999\">HH</td>\n" +
                "\t\t\t<td style=\"text-align: left;width: 20%;line-height: 25px;border-right: 1px solid #999999;border-bottom: 1px solid #999999\">II</td>\n" +
                "\t\t\t<td style=\"text-align: left;width: 20%;line-height: 25px;border-right: 1px solid #999999;border-bottom: 1px solid #999999\">JJ</td>\n" +
                "\t\t</tr>\n" +
                "\t</tbody>\n" +
                "</table>";
        builder.insertHtml(htmlContent);

        // 保存为 .docx 文件
        doc.save("output.docx", SaveFormat.DOCX);

        System.out.println("HTML 转换为 Word 成功！");


    }
}
