package com.ds;

/**
 * @author ds
 * @date 2023/7/21
 * @description
 */
public class T2 {
    /**
     * 示例 1：
     * <p>
     * 输入：strs = ["flower","flow","flight"]
     * 输出："fl"
     * 示例 2：
     * <p>
     * 输入：strs = ["dog","racecar","car"]
     * 输出：""
     * 解释：输入不存在公共前缀。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode.cn/problems/longest-common-prefix
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static void main(String[] args) {
         String[] strs = new String[]{"flower", "flow", "flight"};
       // String[] strs = new String[]{"flower", "flower", "flower", "flower"};
        System.out.println(prefix(strs));
    }

    private static String prefix(String... strs) {
        // 第一个字符串
        String str = strs[0];
        if ("".equals(str)) {
            return "";
        }

        if (strs.length == 1) {
            return str;
        }

        String prefix = "";
        for (int j = 0; j < str.length(); j++) {
            prefix += str.split("")[j];
            for (int i = 1; i < strs.length; i++) {
                if (!strs[i].startsWith(prefix)) {
                    if (prefix.length() == 1) {
                        return "";
                    }
                    return prefix.substring(0, prefix.length() - 1);
                }
            }
        }

        return prefix;
    }


}
