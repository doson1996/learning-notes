package com.ds.jvm.chapter01;

/**
 *
 * @author ds
 */
public class Demo12String {

    public static final int SIZE = 100000;

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
       // method1(SIZE); //35351ms
       // method2(SIZE); //14ms
        method3(SIZE); //11ms
        System.out.println((System.currentTimeMillis() - start) + "ms");

    }

    /**
     * 每次拼接都会创建StringBuilder、String (StringBuilder.toString)
     * @param size
     */
    private static void method1(int size) {
        String str = "";
        for (int i = 0; i < size; i++) {
            str = str + i;
        }
    }

    /**
     * 只创建一个StringBuilder
     * @param size
     */
    private static void method2(int size) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < size; i++) {
            str.append(i);
        }
    }

    /**
     * 通过public StringBuilder(int capacity) 构造函数减少数组扩容次数
     * @param size
     */
    private static void method3(int size) {
        StringBuilder str = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            str.append(i);
        }
    }

}
