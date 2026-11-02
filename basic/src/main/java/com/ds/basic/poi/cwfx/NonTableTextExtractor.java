package com.ds.basic.poi.cwfx;

import java.io.StringReader;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.parser.ParserDelegator;

/**
 * @author ds
 * @date 2026/10/29
 * @description
 */
public class NonTableTextExtractor {
    public static String extract(String html) throws Exception {
        StringBuilder out = new StringBuilder();
        HTMLEditorKit.ParserCallback cb = new HTMLEditorKit.ParserCallback() {
            int tableDepth = 0;
            int styleDepth = 0;  // style/script
            boolean pendingNewline = false; // 避免连续空行

            void newline(int n) {
                // 添加 n 个换行，但压缩连续空行
                int count = 0;
                for (int i = out.length() - 1; i >= 0 && out.charAt(i) == '\n'; i--) count++;
                if (count < n) {
                    for (int i = count; i < n; i++) out.append('\n');
                }
            }

            @Override
            public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {
                if (t == HTML.Tag.TABLE) {
                    tableDepth++;
                    return;
                }
                if (t == HTML.Tag.STYLE || t == HTML.Tag.SCRIPT) {
                    styleDepth++;
                    return;
                }
                if (tableDepth > 0 || styleDepth > 0) return;
                if (t == HTML.Tag.BR) {
                    out.append('\n');
                    return;
                }
                // 块级开始不加，结束加
            }

            @Override
            public void handleEndTag(HTML.Tag t, int pos) {
                if (t == HTML.Tag.TABLE) {
                    tableDepth = Math.max(0, tableDepth - 1);
                    if (tableDepth == 0) newline(2);
                    return;
                }
                if (t == HTML.Tag.STYLE || t == HTML.Tag.SCRIPT) {
                    styleDepth = Math.max(0, styleDepth - 1);
                    return;
                }
                if (tableDepth > 0 || styleDepth > 0) return;
                if (isBlock(t)) newline(1);
            }

            @Override
            public void handleText(char[] data, int pos) {
                if (tableDepth > 0 || styleDepth > 0) return;
                String s = new String(data);
                if (s.trim().isEmpty()) return; // 纯空白忽略
                out.append(s);
            }

            @Override
            public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, int pos) {
                if (tableDepth > 0 || styleDepth > 0) return;
                if (t == HTML.Tag.BR) {
                    out.append('\n');
                } else if (t == HTML.Tag.HR) {
                    newline(1);
                }
            }

            @Override
            public void handleComment(char[] data, int pos) { /* 忽略 MSO 条件注释等 */ }
        };
        new ParserDelegator().parse(new StringReader(html), cb, true);
        // 后处理：压缩连续空白、连续换行
        return out.toString()
                .replace('\u00A0', ' ')          // &nbsp;
                .replaceAll("[ \\t\\x0B\\f\\r]+", " ")
                .replaceAll(" *\\n *", "\n")
                .replaceAll("\\n{3,}", "\n\n")
                .trim();
    }

    private static boolean isBlock(HTML.Tag t) {
        return t == HTML.Tag.P || t == HTML.Tag.DIV || t == HTML.Tag.H1 ||
                t == HTML.Tag.TR || t == HTML.Tag.LI || t == HTML.Tag.BLOCKQUOTE ||
                t == HTML.Tag.PRE || t == HTML.Tag.DT || t == HTML.Tag.DD ||
                t == HTML.Tag.CENTER || t == HTML.Tag.TABLE || t == HTML.Tag.UL || t == HTML.Tag.OL;
    }

}
