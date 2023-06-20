package com.ds.io.chapt01;

import java.io.FileReader;

/**
 * @author ds
 * @date 2023/4/20
 * @description
 */
public class Demo03FileReader {
    public static void main(String[] args) throws Exception {
        FileReader fileReader = new FileReader("a.txt");
        int b;
        while ((b = fileReader.read()) != -1) {
            System.out.print((char) b);
        }

        System.out.println();

        fileReader = new FileReader("a.txt");
        char[] chars = new char[2];
        int len;
        while ((len = fileReader.read(chars)) != -1) {
            System.out.print(new String(chars, 0, len));
        }
        fileReader.close();
    }
}
