package com.ds.io.chapt01;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author ds
 * @date 2023/4/18
 * @description 文件拷贝
 */
public class Demo02Copy {
    public static void main(String[] args) {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream("a.txt");
            fos = new FileOutputStream("b.txt");
            byte[] data = new byte[1024];
            // 读取到数据的长度
            int len;
            while ((len = fis.read(data)) != -1) {
                System.out.println("b = " + len);
                System.out.println(new String(data, 0, len));
                fos.write(data);
            }
        } catch (Exception e) {
            System.err.println(e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    System.err.println(e);
                }
            }

            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
        }
    }
}
