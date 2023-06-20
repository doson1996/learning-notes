package com.ds.io.chapt01;

import java.io.FileWriter;

/**
 * @author ds
 * @date 2023/4/20
 * @description
 */
public class Demo04FileWriter {
    public static void main(String[] args) throws Exception {
        // 不加true会清空文件
        FileWriter fileWriter = new FileWriter("a.txt", true);
        fileWriter.write("张三");
        fileWriter.close();
    }
}
