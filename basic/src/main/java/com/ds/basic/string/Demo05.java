package com.ds.basic.string;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ds
 * @date 2024/4/9
 * @description
 */
public class Demo05 {
    public static void main(String[] args) {
        String str = "*company*，成立于[reg_date]";
        boolean matches = Pattern.matches(".*\\[.*].*", str);
        System.out.println("matches = " + matches);

        Pattern pattern = Pattern.compile(".*\\[.*].*");
        Matcher matcher = pattern.matcher(str);
        int start = matcher.start();
        System.out.println("start = " + start);
        int end = matcher.end();
        System.out.println("end = " + end);
    }
}
