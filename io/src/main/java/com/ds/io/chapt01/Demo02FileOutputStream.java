package com.ds.io.chapt01;

import java.io.FileOutputStream;
import java.nio.charset.Charset;

/**
 * @author ds
 * @date 2023/4/18
 * @description 文件输出流
 */
public class Demo02FileOutputStream {
    public static void main(String[] args) throws Exception {
        // true 续写，创建对象时不会清空文件
        FileOutputStream fo = new FileOutputStream("a.txt", true);
        // 写入
        fo.write("张三".getBytes(Charset.defaultCharset()));
        // 换行
        fo.write("\r\n".getBytes());
        fo.write("李四".getBytes(Charset.defaultCharset()));
        fo.close();
    }
}
