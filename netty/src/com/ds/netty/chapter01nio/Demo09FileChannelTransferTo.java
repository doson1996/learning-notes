package com.ds.netty.chapter01nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @author ds
 * @date 2023/4/26
 * @description
 */
public class Demo09FileChannelTransferTo {
    public static void main(String[] args) {
        try (FileChannel from = new FileInputStream("a.txt").getChannel();
             FileChannel to = new FileOutputStream("to.txt").getChannel();
        ) {
           // 文件大小
            long size = from.size();
            // 剩余未传输字节
            long remainder = size;
            for (; remainder > 0; ) {
                // 起始位置
                long position = size - remainder;
                // 传输字节, transfer效率高，底层用零拷贝，一次最多传2g
                long transfer = from.transferTo(position, remainder, to);
                // 更新剩余未传输字节
                remainder = remainder - transfer;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void v1() {
        try (FileChannel from = new FileInputStream("a.txt").getChannel();
             FileChannel to = new FileOutputStream("to.txt").getChannel();
        ) {
            // 效率高，底层用零拷贝，一次最多传2g
            from.transferTo(0, from.size(), to);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
