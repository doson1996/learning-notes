package com.ds.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author ds
 * @date 2023/3/22
 * @description
 */
public class Test {

    public static void main(String[] args) {
        SimpleStack<String> stack = new SimpleStack<>();
        stack.push("1");
        stack.push("2");
        stack.push("3");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push("4");
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(new StringBuffer("abc").reverse());
        System.out.println(reverse("abc"));
        // 检查符号是否成对出现
        //  System.out.println(isValid("[]"));
    }

    /**
     * 反转字符串
     *
     * @param str
     * @return
     */
    public static String reverse(String str) {
        StringBuilder sb = new StringBuilder();
        SimpleStack<String> stack = new SimpleStack<>();
        String[] split = str.split("");
        for (String s : split) {
            stack.push(s);
        }

        int size = stack.size();
        for (int i = 0; i < size; i++) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }


    public static boolean isValid(String s) {
        // 括号之间的对应规则
        Map<Character, Character> mappings = new HashMap<>();
        mappings.put(')', '(');
        mappings.put('}', '{');
        mappings.put(']', '[');
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (mappings.containsKey(chars[i])) {
                char topElement = stack.empty() ? '#' : stack.pop();
                if (topElement != mappings.get(chars[i])) {
                    return false;
                }
            } else {
                stack.push(chars[i]);
            }
        }
        return stack.isEmpty();
    }
}
