package com.ds.basic.poi.cwfx;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

/**
 * @author ds
 * @date 2026/10/28
 * @description
 */
public class JdkHtmlTableExtractor {

    public static List<Map<String, String>> extract(String html) throws Exception {
        final List<List<String>> rows = new ArrayList<>();
        final List<Boolean> headerFlags = new ArrayList<>(); // 记录每行是否为表头
        final int[] tableDepth = {0};

        HTMLEditorKit.ParserCallback cb = new HTMLEditorKit.ParserCallback() {
            // 行/单元格状态
            List<String> currentCells = new ArrayList<>();
            StringBuilder cellBuf = new StringBuilder();
            boolean inCell = false;
            boolean currentRowIsHeader = false;

            @Override
            public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {
                if (t == HTML.Tag.TABLE) {
                    tableDepth[0]++;
                    return;
                }
                if (tableDepth[0] != 1) return; // 只处理最外层表格，忽略嵌套表格

                if (t == HTML.Tag.TR) {
                    currentCells = new ArrayList<>();
                    currentRowIsHeader = false;
                } else if (t == HTML.Tag.TH) {
                    inCell = true;
                    cellBuf.setLength(0);
                    currentRowIsHeader = true;
                } else if (t == HTML.Tag.TD) {
                    inCell = true;
                    cellBuf.setLength(0);
                }
            }

            @Override
            public void handleText(char[] data, int pos) {
                if (inCell && tableDepth[0] == 1) {
                    cellBuf.append(data);
                }
            }

            @Override
            public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, int pos) {
                // 单元格里的 <br> 转成空格，避免文字粘连
                if (inCell && t == HTML.Tag.BR) {
                    cellBuf.append(' ');
                }
            }

            @Override
            public void handleEndTag(HTML.Tag t, int pos) {
                if (t == HTML.Tag.TABLE) {
                    tableDepth[0]--;
                    return;
                }
                if (tableDepth[0] != 1) return;

                if ((t == HTML.Tag.TD || t == HTML.Tag.TH) && inCell) {
                    inCell = false;
                    // 压缩连续空白
                    currentCells.add(cellBuf.toString().replaceAll("\\s+", " ").trim());
                } else if (t == HTML.Tag.TR) {
                    if (!currentCells.isEmpty()) {
                        rows.add(new ArrayList<>(currentCells));
                        headerFlags.add(currentRowIsHeader);
                    }
                }
            }
        };

        new ParserDelegator().parse(new StringReader(html), cb, true);

        // 组装成 List<Map<字段名, 值>>
        List<Map<String, String>> result = new ArrayList<>();
        if (rows.isEmpty()) return result;

        // 找出第一行作为表头（若第一行不是 th，也默认当表头使用）
        List<String> headers = rows.get(0);

        for (int i = 1; i < rows.size(); i++) {
            List<String> cells = rows.get(i);
            if (cells.size() != headers.size()) continue; // 跳过列数不匹配的行
            Map<String, String> row = new HashMap<>();
            for (int j = 0; j < headers.size(); j++) {
                String key = headers.get(j);
                row.put(key.replace(" ", "").replace(" ", ""), cells.get(j).replace(" ", "").replace(" ", ""));
            }
            result.add(row);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        String html = "<html><body><table>"
                + "<tr><th>姓名</th><th>年龄</th><th>城市</th></tr>"
                + "<tr><td>张三</td><td>28</td><td>北京</td></tr>"
                + "<tr><td>李四</td><td>32</td><td>上海</td></tr>"
                + "</table></body></html>";

        for (Map<String, String> row : extract(html)) {
            System.out.println(row.get("姓名") + " / " + row.get("年龄") + " / " + row.get("城市"));
        }
    }
}