package com.ds.jvm.chapter01;

/**
 * @author ds
 * @date 2021/6/1 23:31
 */
public class Demo15Intern1 {
    public static void main(String[] args) {
        String s1 = new String("1") + new String("1");
        //s1.intern();
        String s2 = "11";
        String s3 = s1.intern();
        System.out.println(s1 == s2);
        System.out.println(s3 == s2);
    }
}
