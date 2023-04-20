package com.ds.io.chapt01;

import java.io.FileInputStream;

/**
 * @author ds
 * @date 2023/4/18
 * @description
 */
public class Demo01FileInputStream {
    public static void main(String[] args) throws Exception {
        FileInputStream fis = new FileInputStream("a.txt");
//        byte[] data = new byte[1024];
//        int b;
//        while ((b = fis.read(data)) != -1) {
//
//        }
//        fis.close();
//        System.out.println("data = " + new String(data));
        int b;
        while ((b = fis.read()) != -1) {
            System.out.println("b = " + (char)b);
        }
    }
}
