package com.ds.jvm.chapter01;

import sun.misc.Version;

/**
 * @Author ds
 * @Date 2021/3/17 15:18
 * @Description
 *          运行结果：
 *                  true
 *                  false
 *                  false
 *         在main()执行之前那个"java"字符串早就已经在字符串常量池里了{@link Version}，所以会有这样的结果。
 *
 */
public class Demo00StringIntern {

    public static void main(String[] args) {

        String s1 = new String("计算机") + new String("软件");
        System.out.println(s1.intern() == s1);

        String s2 = new String("ja") + new String("va");
        System.out.println(s2.intern() == s2);

        //String s4 = "测试";
        String s4 = new String("测试");

        String s3 = new String("测") + new String("试");
        System.out.println(s3.intern() == s3);

    }
}
