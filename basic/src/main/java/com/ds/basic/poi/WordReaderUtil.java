package com.ds.basic.poi;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;

/**
 * @author ds
 * @date 2026/8/4
 * @description
 */
public class WordReaderUtil {
    /**
     * 场景1：读取Word文档中的所有纯文本内容
     *
     * @param filePath 文件路径
     * @return 完整文本字符串
     */
    public static String readFullText(String filePath) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             XWPFDocument document = new XWPFDocument(fis)) {

            StringBuilder text = new StringBuilder();
            // 遍历所有段落
            for (XWPFParagraph paragraph : document.getParagraphs()) {
                text.append(paragraph.getText()).append("\n");
            }
            return text.toString();
        }
    }

    /**
     * 场景2：读取Word文档中的所有表格数据
     *
     * @param filePath 文件路径
     * @return List<List < String>> 每个元素代表一个表格，内部List代表行，再内部代表单元格
     */
    public static List<List<List<String>>> readTables(String filePath) throws IOException {
        List<List<List<String>>> allTables = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             XWPFDocument document = new XWPFDocument(fis)) {

            // 获取文档中所有表格
            List<XWPFTable> tables = document.getTables();

            for (XWPFTable table : tables) {
                List<List<String>> currentTableData = new ArrayList<>();
                // 遍历行
                for (XWPFTableRow row : table.getRows()) {
                    List<String> rowData = new ArrayList<>();
                    // 遍历单元格
                    for (XWPFTableCell cell : row.getTableCells()) {
                        // 清理单元格内的空白字符
                        String cellText = cell.getText().trim();
                        rowData.add(cellText);
                    }
                    currentTableData.add(rowData);
                }
                allTables.add(currentTableData);
            }
        }
        return allTables;
    }

    /**
     * 场景3：通过【书签】读取指定信息
     * 前提：Word文档中必须预先插入书签 (Insert -> Bookmark)
     *
     * @param filePath     文件路径
     * @param bookmarkName 书签名称
     * @return 书签包裹的文本内容，如果未找到返回 null
     */
    public static String readByBookmark(String filePath, String bookmarkName) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath);
             XWPFDocument document = new XWPFDocument(fis)) {

            // POI 对书签的支持相对底层，需要遍历文档元素
            // 注意：简单的 document.getBookmarks() 在某些版本可能不可用或行为不一致
            // 这里使用更通用的方式：查找 CTBookmark

            for (IBodyElement element : document.getBodyElements()) {
                if (element instanceof XWPFParagraph) {
                    XWPFParagraph paragraph = (XWPFParagraph) element;

                    // 检查段落中是否包含指定的书签开始标记
                    boolean foundStart = false;
                    StringBuilder content = new StringBuilder();

                    for (int i = 0; i < paragraph.getCTP().sizeOfBookmarkStartArray(); i++) {
                        CTBookmark bookmark = paragraph.getCTP().getBookmarkStartArray(i);
                        if (bookmarkName.equals(bookmark.getName())) {
                            foundStart = true;
                            break;
                        }
                    }

                    if (foundStart) {
                        // 简单策略：如果找到书签，通常该段落的文本即为目标，或者需要更复杂的逻辑判断书签结束位置
                        // 对于大多数简单模板，书签往往单独占据一个Run或Paragraph
                        return paragraph.getText();
                    }
                }
            }
            return null;
        }
    }

    /**
     * 场景4：通过【关键词/正则】读取指定信息 (最常用)
     * 例如：文档中有 "姓名：张三"，想提取 "张三"
     *
     * @param filePath   文件路径
     * @param keyPattern 匹配键的正则表达式 (例如 "姓名[：:](.*)")
     * @param groupIndex 捕获组的索引 (通常为1)
     * @return 提取到的值，未找到返回 null
     */
    public static String readByKeyword(String filePath, String keyPattern, int groupIndex) throws IOException {
        // 编译正则，忽略大小写
        Pattern pattern = Pattern.compile(keyPattern, Pattern.CASE_INSENSITIVE);

        try (FileInputStream fis = new FileInputStream(filePath);
             XWPFDocument document = new XWPFDocument(fis)) {

            for (XWPFParagraph paragraph : document.getParagraphs()) {
                String text = paragraph.getText();
                if (text != null && !text.isEmpty()) {
                    Matcher matcher = pattern.matcher(text);
                    if (matcher.find()) {
                        return matcher.group(groupIndex).trim();
                    }
                }
            }
        }
        return null;
    }

    /**
     * 批量通过关键词Map提取信息
     *
     * @param filePath 文件路径
     * @param patterns Map<KeyName, RegexPattern>
     * @return Map<KeyName, ExtractedValue>
     */
    public static Map<String, String> readMultipleKeywords(String filePath, Map<String, String> patterns) throws IOException {
        Map<String, String> results = new HashMap<>();
        // 预编译所有正则
        Map<String, Pattern> compiledPatterns = new HashMap<>();
        for (Map.Entry<String, String> entry : patterns.entrySet()) {
            compiledPatterns.put(entry.getKey(), Pattern.compile(entry.getValue(), Pattern.CASE_INSENSITIVE));
        }

        try (FileInputStream fis = new FileInputStream(filePath);
             XWPFDocument document = new XWPFDocument(fis)) {

            for (XWPFParagraph paragraph : document.getParagraphs()) {
                String text = paragraph.getText();
                if (text == null || text.isEmpty()) continue;

                // 遍历所有待匹配的key
                for (Map.Entry<String, Pattern> entry : compiledPatterns.entrySet()) {
                    // 如果该key还没有被提取到，则尝试匹配
                    if (!results.containsKey(entry.getKey())) {
                        Matcher matcher = entry.getValue().matcher(text);
                        if (matcher.find()) {
                            results.put(entry.getKey(), matcher.group(1).trim());
                        }
                    }
                }

                // 优化：如果所有key都找到了，提前退出循环
                if (results.size() == patterns.size()) {
                    break;
                }
            }
        }
        return results;
    }

    // ================= 测试主方法 =================
    public static void main(String[] args) {
        // 替换为你的实际文件路径
        String filePath = "D:\\haizhi\\doc\\2025\\03.尽调\\数字分析助手\\报告模板\\小微公司授信业务调查报告.docx";

        try {
            System.out.println("--- 1. 全文本预览 ---");
            // System.out.println(readFullText(filePath));

            System.out.println("--- 2. 关键词提取测试 ---");
            // 假设文档里有 "注册资金：1万元；"
            String contractNo = readByKeyword(filePath, "注册资金[：:](.*)", 1);
            System.out.println("注册资金: " + contractNo);

            System.out.println("--- 3. 批量提取测试 ---");
            Map<String, String> patterns = new HashMap<>();
            patterns.put("注册资金", "注册资金[：:](.*)");

            Map<String, String> results = readMultipleKeywords(filePath, patterns);
            results.forEach((k, v) -> System.out.println(k + ": " + v));

            System.out.println("--- 4. 表格数据测试 ---");
            List<List<List<String>>> tables = readTables(filePath);
            System.out.println("找到表格数量: " + tables.size());
            if (!tables.isEmpty()) {
                System.out.println("第一个表格第一行数据: " + tables.get(0).get(0));
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("文件读取失败，请检查路径是否正确或文件是否被占用。");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("发生未知错误");
        }
    }
}
