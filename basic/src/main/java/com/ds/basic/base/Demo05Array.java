package com.ds.basic.base;

import java.util.Arrays;

/**
 * for 数据量少的时候更快，更直观、容易理解
 * System.arraycopy 数据量大的时候更快
 * @author ds
 */
public class Demo05Array {

    private static int COUNT = 30000;
    private static String DELIMITER = ",";

    public static void main(String[] args) {
        String[] arr1 = new String[COUNT];
        String[] arr2 = new String[COUNT];

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < COUNT; i++) {
            sb.append(i).append(DELIMITER);
        }
        String str = sb.toString();
        String[] split = str.split(DELIMITER);

        long start1 = System.nanoTime();
        for (int i = 0; i < split.length; i++) {
            arr1[i] = split[i];
        }
        long end1 = System.nanoTime();
        System.out.println("for耗时 " + (end1 - start1) + "纳秒");

        long start2 = System.nanoTime();
        System.arraycopy(split, 0, arr2, 0, split.length);
        long end2 = System.nanoTime();
        System.out.println("System.arraycopy耗时 " + (end2 - start2) + "纳秒");

       // System.out.println(Arrays.toString(arr1));
    }
}
