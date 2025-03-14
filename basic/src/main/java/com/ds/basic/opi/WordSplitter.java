package com.ds.basic.opi;

import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Set;

import com.aspose.words.Body;
import com.aspose.words.BorderCollection;
import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.HeaderFooterType;
import com.aspose.words.Node;
import com.aspose.words.NodeType;
import com.aspose.words.Paragraph;
import com.aspose.words.SaveFormat;
import com.aspose.words.StyleIdentifier;


public class WordSplitter {

    // 输出目录
    static String outputDir = "D://docx//";

    public static void main(String[] args) throws Exception {
        // 输入的Word文件路径
        String inputFilePath = "D://hy.docx";

        Document doc = new Document(inputFilePath);

        // 初始化拆分工具
        DocumentPartSaver saver = new DocumentPartSaver(doc);

        int i = 0;
        // 遍历文档中的段落
        for (Paragraph paragraph : (Iterable<Paragraph>) doc.getChildNodes(NodeType.PARAGRAPH, true)) {
            if (isTitle(paragraph)) {
                if (i++ != 0) {
                    saver.saveCurrentPart();
                }
                saver.startNewPart();
            }

            if (paragraph.getParentNode().getNodeType() == NodeType.TABLE) {
                // 如果段落属于表格，则直接跳过，避免重复处理
                continue;
            }

            saver.addParagraph(paragraph);
        }

        // 保存最后一个部分
        saver.saveCurrentPart();
    }

    /**
     * 检测段落是否为标题
     */
    private static boolean isTitle(Paragraph paragraph) {
        // 判断段落样式是否为标题样式（例如 Heading 1、Heading 2 等）
        String type = paragraph.getParagraphFormat().getStyle().getName();
        return type != null && type.toLowerCase().startsWith("heading 1");
    }

    /**
     * 用于保存文档部分的工具类
     */
    private static class DocumentPartSaver {
        // 保存原始文档的引用
        private Document sourceDoc;
        private Document currentDoc;
        private DocumentBuilder builder;
        private int partNumber = 1;
        private Set<Node> importedTables = new HashSet<>();


        public DocumentPartSaver(Document sourceDoc) throws Exception {
            // 保存原始文档的引用
            this.sourceDoc = sourceDoc;
            startNewPart();
        }

        /**
         * 开始新部分
         */
        public void startNewPart() throws Exception {
            currentDoc = new Document();
            builder = new DocumentBuilder(currentDoc);

            // 移除默认的空白段落
            if (currentDoc.getFirstSection().getBody().getParagraphs().getCount() > 0) {
                currentDoc.getFirstSection().getBody().getParagraphs().removeAt(0);
            }

            // 复制源文档的页眉和页脚设置
            copyHeadersAndFooters(sourceDoc, currentDoc);
        }

        /**
         * 复制源文档的页眉和页脚到目标文档
         */
        private void copyHeadersAndFooters(Document sourceDoc, Document targetDoc) {
            for (com.aspose.words.Section sourceSection : sourceDoc.getSections()) {
                com.aspose.words.Section targetSection = targetDoc.getLastSection();

                // 确保目标部分已初始化页眉页脚集合
                if (targetSection.getHeadersFooters().getCount() == 0) {
                    targetSection.appendChild(new com.aspose.words.HeaderFooter(targetDoc, HeaderFooterType.HEADER_PRIMARY));
                }

                for (com.aspose.words.HeaderFooter headerFooter : sourceSection.getHeadersFooters()) {
                    // 导入页眉页脚节点
                    Node importedHeaderFooter = targetDoc.importNode(headerFooter, true);

                    // 获取目标部分中对应的页眉页脚类型
                    int hfType = headerFooter.getHeaderFooterType();
                    com.aspose.words.HeaderFooter targetHeaderFooter = targetSection.getHeadersFooters().getByHeaderFooterType(hfType);

                    if (targetHeaderFooter == null) {
                        // 如果目标部分中不存在对应类型的页眉页脚，则添加
                        targetSection.getHeadersFooters().add((com.aspose.words.HeaderFooter) importedHeaderFooter);
                    } else {
                        // 如果存在，则替换内容
                        targetHeaderFooter.getChildNodes().clear();
                        // 使用 iterator 遍历子节点
                        for (Node childNode : ((com.aspose.words.HeaderFooter) importedHeaderFooter).getChildNodes().toArray()) {
                            Node clonedChild = targetDoc.importNode(childNode, true);
                            targetHeaderFooter.appendChild(clonedChild);
                        }
                    }
                }
            }
        }

        /**
         * 添加段落到当前部分
         */
        public void addParagraph(Paragraph paragraph) throws Exception {
            // 处理表格
            int nodeType = paragraph.getParentNode().getNodeType();
            if (nodeType == NodeType.CELL) {
                // 如果段落属于表格，则导入整个表格
                Node table = paragraph.getAncestor(NodeType.TABLE);
                if (!isTableAlreadyImported(table)) {
                    Node importedTable = currentDoc.importNode(table, true);
                    Body body = currentDoc.getFirstSection().getBody();
                    body.appendChild(importedTable);

                    // 设置表格样式
                    com.aspose.words.Table asposeTable = (com.aspose.words.Table) importedTable;
//                    asposeTable.autoFit(AutoFitBehavior.AUTO_FIT_TO_CONTENTS);
                    // 复制表格样式和单元格样式
                    copyTableStyle((com.aspose.words.Table) table, asposeTable);

                    // 记录已导入的表格
                    importedTables.add(table);

                    // 处理嵌套表格
                    handleNestedTables(asposeTable);
                }
            } else if (nodeType != NodeType.COMMENT) {  // 如果段落不属于表格且不是评论，则导入段落
                // 否则只导入段落
                Node importedNode = currentDoc.importNode(paragraph, true);
                // 将段落插入到目标文档的主体部分
                Body body = currentDoc.getFirstSection().getBody();
                body.appendChild(importedNode);

                // 复制段落格式
                copyParagraphFormat(paragraph, (Paragraph) importedNode);

                // 复制字体样式
                copyFontStyles(paragraph, (Paragraph) importedNode);
            }
        }

        /**
         * 处理嵌套表格，确保嵌套表格不会被重复导入
         */
        private void handleNestedTables(com.aspose.words.Table table) {
            for (int rowIndex = 0; rowIndex < table.getRows().getCount(); rowIndex++) {
                com.aspose.words.Row row = table.getRows().get(rowIndex);
                for (int cellIndex = 0; cellIndex < row.getCells().getCount(); cellIndex++) {
                    com.aspose.words.Cell cell = row.getCells().get(cellIndex);
                    int childCount = cell.getChildNodes(NodeType.ANY, true).getCount();
                    for (int i = 0; i < childCount; i++) {
                        Node childNode = cell.getChildNodes(NodeType.ANY, true).get(i);
                        if (childNode.getNodeType() == NodeType.TABLE) {
                            com.aspose.words.Table nestedTable = (com.aspose.words.Table) childNode;
                            if (!isTableAlreadyImported(nestedTable)) {
                                importedTables.add(nestedTable); // 记录嵌套表格
                            }
                        }
                    }
                }
            }
        }

        /**
         * 复制段落中运行对象的字体样式
         */
        private void copyFontStyles(Paragraph sourceParagraph, Paragraph targetParagraph) {
            if (sourceParagraph == null || targetParagraph == null) {
                return;
            }

            // 遍历源段落中的所有运行对象
            for (com.aspose.words.Run sourceRun : sourceParagraph.getRuns()) {
                // 找到对应的运行对象
                com.aspose.words.Run targetRun = null;
                for (com.aspose.words.Run run : targetParagraph.getRuns()) {
                    if (run.getText().equals(sourceRun.getText())) {
                        targetRun = run;
                        break;
                    }
                }

                if (targetRun == null) {
                    // 如果没有找到对应的运行对象，则创建新的运行对象
                    targetRun = new com.aspose.words.Run(targetParagraph.getDocument(), sourceRun.getText());
                    targetParagraph.getRuns().add(targetRun);
                }

                // 复制字体名称
                targetRun.getFont().setName(sourceRun.getFont().getName());

                // 复制字体大小
                targetRun.getFont().setSize(sourceRun.getFont().getSize());

                // 复制字体颜色
                targetRun.getFont().setColor(sourceRun.getFont().getColor());

                // 复制加粗属性
                targetRun.getFont().setBold(sourceRun.getFont().getBold());

                // 复制斜体属性
                targetRun.getFont().setItalic(sourceRun.getFont().getItalic());

                // 复制下划线属性
                targetRun.getFont().setUnderline(sourceRun.getFont().getUnderline());
            }
        }

        /**
         * 复制段落格式
         */
        private void copyParagraphFormat(Paragraph sourceParagraph, Paragraph targetParagraph) {
            if (sourceParagraph != null && targetParagraph != null) {
                // 复制段落对齐方式
                targetParagraph.getParagraphFormat().setAlignment(sourceParagraph.getParagraphFormat().getAlignment());

                // 复制段落行距
                double lineSpacing = sourceParagraph.getParagraphFormat().getLineSpacing();
                targetParagraph.getParagraphFormat().setLineSpacing(lineSpacing);

                // 复制段落行距规则
                targetParagraph.getParagraphFormat().setLineSpacingRule(sourceParagraph.getParagraphFormat().getLineSpacingRule());

                // 复制段落前后间距
                targetParagraph.getParagraphFormat().setSpaceAfter(sourceParagraph.getParagraphFormat().getSpaceAfter());
                targetParagraph.getParagraphFormat().setSpaceBefore(sourceParagraph.getParagraphFormat().getSpaceBefore());

                // 复制段落缩进
                targetParagraph.getParagraphFormat().setFirstLineIndent(sourceParagraph.getParagraphFormat().getFirstLineIndent());
                targetParagraph.getParagraphFormat().setLeftIndent(sourceParagraph.getParagraphFormat().getLeftIndent());
                targetParagraph.getParagraphFormat().setRightIndent(sourceParagraph.getParagraphFormat().getRightIndent());
            }
        }

        private void copyTableStyle(com.aspose.words.Table sourceTable, com.aspose.words.Table targetTable) throws Exception {
            if (sourceTable != null && targetTable != null) {
                // 替代逻辑：检查样式标识符是否为默认值
                int styleIdentifier = sourceTable.getStyleIdentifier();
                boolean isDefaultStyle = styleIdentifier == StyleIdentifier.NORMAL || styleIdentifier == -1;

                if (!isDefaultStyle) {
                    try {
                        // 如果有预定义样式标识符，直接设置
                        targetTable.setStyleIdentifier(styleIdentifier);
                    } catch (Exception e) {
                        System.out.println("无法设置样式标识符：" + e.getMessage());
                    }

                } else {
                    // 如果没有预定义样式标识符，尝试使用自定义样式名称
                    String styleName = sourceTable.getStyleName();
                    if (styleName != null && !styleName.isEmpty()) {
                        targetTable.setStyleName(styleName);
                    }
                }

                // 设置表格样式选项
                targetTable.setStyleOptions(sourceTable.getStyleOptions());

                // 复制单元格样式
                for (int rowIndex = 0; rowIndex < sourceTable.getRows().getCount(); rowIndex++) {
                    com.aspose.words.Row sourceRow = sourceTable.getRows().get(rowIndex);
                    com.aspose.words.Row targetRow = targetTable.getRows().get(rowIndex);

                    for (int cellIndex = 0; cellIndex < sourceRow.getCells().getCount(); cellIndex++) {
                        com.aspose.words.Cell sourceCell = sourceRow.getCells().get(cellIndex);
                        com.aspose.words.Cell targetCell = targetRow.getCells().get(cellIndex);

                        // 复制单元格边框
                        if (sourceCell.getCellFormat() != null) {
                            com.aspose.words.Shading sourceShading = sourceCell.getCellFormat().getShading();
                            com.aspose.words.Shading targetShading = targetCell.getCellFormat().getShading();

                            // 复制背景色
                            if (sourceShading != null) {
                                targetShading.setBackgroundPatternColor(sourceShading.getBackgroundPatternColor());
                            }

                            // 复制边框属性
                            copyCellBorders(sourceCell, targetCell);
                        }

                    }
                }
            }
        }


        /**
         * 复制单元格边框属性
         */
        private void copyCellBorders(com.aspose.words.Cell sourceCell, com.aspose.words.Cell targetCell) throws Exception {
            if (sourceCell.getCellFormat() != null && targetCell.getCellFormat() != null) {
                BorderCollection sourceBorders = sourceCell.getCellFormat().getBorders();
                BorderCollection targetBorders = targetCell.getCellFormat().getBorders();

                // 遍历所有边框类型
                for (int borderType : com.aspose.words.BorderType.getValues()) {
                    // 检查 borderType 是否在有效范围内
                    if (borderType >= 0 && borderType < sourceBorders.getCount() && borderType < targetBorders.getCount()) {
                        com.aspose.words.Border sourceBorder = sourceBorders.get(borderType);
                        com.aspose.words.Border targetBorder = targetBorders.get(borderType);

                        if (sourceBorder != null && targetBorder != null) {
                            // 复制边框样式
                            targetBorder.setLineStyle(sourceBorder.getLineStyle());
                            targetBorder.setColor(sourceBorder.getColor());
                            targetBorder.setLineWidth(sourceBorder.getLineWidth());
                        }
                    } else {
                        // 如果 borderType 不合法，记录日志或跳过
//                        System.err.println("Invalid borderType: " + borderType);
                    }
                }
            }
        }

        /**
         * 检查目标文档中是否已包含指定表格
         */
        private boolean isTableAlreadyImported(Node table) {
            // 使用表格的唯一标识（如 hashCode）进行判断
            return importedTables.stream()
                    .anyMatch(importedTable -> importedTable.hashCode() == table.hashCode());
        }

        /**
         * 保存当前部分
         */
        public void saveCurrentPart() throws Exception {
            if (currentDoc == null || currentDoc.getChildNodes(NodeType.ANY, true).getCount() == 0) {
                return; // 如果当前部分为空，则不保存
            }

            // 保存为新的 Word 文件
            String outputFilePath = outputDir + "output_part_" + partNumber + ".docx";
            try (FileOutputStream fos = new FileOutputStream(outputFilePath)) {
                currentDoc.save(fos, SaveFormat.DOCX);
            }

            partNumber++;
        }
    }

}