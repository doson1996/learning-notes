package com.ds.basic.io.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * delete无需解释，为直接删除，
 * deleteOnExit文档解释为：在虚拟机终止时，请求删除此抽象路径名表示的文件或目录。
 * 也就是说，程序运行deleteOnExit成功后，File并没有直接删除，而是在虚拟机正常运行结束后才会删除。
 *
 * @author ds
 * @date 2022/3/17 23:05
 */
public class Demo01File {
    public static void main(String[] args) throws IOException {
        File file = new File("a.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write("95");
        writer.flush();
        System.out.println(file.getAbsolutePath());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        file.deleteOnExit();
    }
}
