package com.ds.dp.singleton;

/**
 * @Author ds
 * @Date 2021/3/8 14:49
 * @Version 1.0
 * @Description
 */
public class TestOneExtend {
    public static void main(String[] args) {
        OneExtend oneExtend1 = OneExtend.getInstance();
        OneExtend oneExtend2 = OneExtend.getInstance();
        OneExtend oneExtend3 = OneExtend.getInstance();
        OneExtend oneExtend4 = OneExtend.getInstance();
        System.out.println(oneExtend1);
        System.out.println(oneExtend2);
        System.out.println(oneExtend3);
        System.out.println(oneExtend4);
    }
}
