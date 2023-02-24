package com.ds.jvm.chapter01;

/**
 * @author ds
 * @date 2021/5/30 22:06
 */
public class Demo13Intern {
    public static void main(String[] args) {
        /**
         * 确保变量指向的是字符串常量池的方法
         * ① String s2 = "test"; //字面量定义的方式
         * ② 字符串调用 intern 方法
         */

        String s1 = new String("test");
        String s2 = "test";
        String s3 = new String("ja") + new String("va");

        System.out.println(s1.equals(s2));
        System.out.println(s1 == s2);
        System.out.println(s1.intern() == s2);
        System.out.println(s3.intern() == s3);
    }
}
