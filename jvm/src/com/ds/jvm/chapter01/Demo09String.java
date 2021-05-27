package com.ds.jvm.chapter01;

/**
 * jdk8 char[]
 * jdk9 byte[]
 *
 *
 * @author ds
 */
public class Demo09String {

    public static void main(String[] args) {
        String str = "abc";
        char[] chars = {'t', 'e', 's', 't'};
        ex(str, chars);
        System.out.println(str);
        System.out.println(chars);
    }

    private static void ex(String str, char[] chars) {
        str = "a";
        chars[0] = 'c';
    }
}
