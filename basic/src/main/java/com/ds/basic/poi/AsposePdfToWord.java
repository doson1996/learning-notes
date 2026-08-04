package com.ds.basic.poi;


import com.aspose.pdf.DocSaveOptions;
import com.aspose.pdf.Document;

/**
 * @author ds
 * @date 2026/8/4
 * @description
 */
public class AsposePdfToWord {
    public static void main(String[] args) throws Exception {
        // 加载 PDF
        Document pdfDoc = new Document("D://信息系统安全评测报告.pdf");

        // 配置 Word 保存选项
        DocSaveOptions saveOptions = new DocSaveOptions();
        // 输出格式：DocX 或 Doc
        saveOptions.setFormat(DocSaveOptions.DocFormat.DocX);
        // 识别模式：Flow（保留布局）或 Textbox（精确定位）
        saveOptions.setMode(DocSaveOptions.RecognitionMode.Flow);
        // 其他选项按需设置...

        // 保存为 Word
        pdfDoc.save("D://信息系统安全评测报告.docx", saveOptions);
        System.out.println("转换完成！");
    }
}
