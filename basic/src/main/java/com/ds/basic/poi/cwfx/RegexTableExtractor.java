package com.ds.basic.poi.cwfx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ds
 * @date 2026/10/28
 * @description
 */
public class RegexTableExtractor {

    private static final Pattern ROW = Pattern.compile("(?is)<tr[^>]*>(.*?)</tr>");

    private static final Pattern CELL = Pattern.compile("(?is)<t[dh][^>]*>(.*?)</t[dh]>");

    private static final Pattern TAG = Pattern.compile("(?is)<[^>]+>");

    public static List<Map<String, String>> extract(String html) {
        List<Map<String, String>> result = new ArrayList<>();
        List<List<String>> rows = new ArrayList<>();

        Matcher rm = ROW.matcher(html);
        while (rm.find()) {
            List<String> cells = new ArrayList<>();
            Matcher cm = CELL.matcher(rm.group(1));
            while (cm.find()) {
                // 去掉内层标签，解码常见实体
                String text = TAG.matcher(cm.group(1)).replaceAll("")
                        .replace("&nbsp;", " ")
                        .replace("&amp;", "&")
                        .replace("&lt;", "<")
                        .replace("&gt;", ">")
                        .replaceAll("\\s+", " ").trim();
                cells.add(text);
            }
            if (!cells.isEmpty()) rows.add(cells);
        }

        if (rows.isEmpty()) return result;
        List<String> headers = rows.get(0);
        for (int i = 1; i < rows.size(); i++) {
            List<String> cells = rows.get(i);
            if (cells.size() != headers.size()) continue;
            Map<String, String> row = new HashMap<>();
            for (int j = 0; j < headers.size(); j++) row.put(headers.get(j), cells.get(j));
            result.add(row);
        }
        return result;
    }
}
