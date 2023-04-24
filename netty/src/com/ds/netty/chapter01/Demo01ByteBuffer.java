package com.ds.netty.chapter01;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author ds
 * @date 2023/4/20
 * @description
 */
@Slf4j
public class Demo01ByteBuffer {
    public static void main(String[] args) throws Exception {
        // 获得FileChannel
        FileChannel channel = new FileInputStream("a.txt").getChannel();
        // 准备缓冲区 10字节
        ByteBuffer buffer = ByteBuffer.allocate(10);
        // 从channel中读数据到buffer中
        int len;
        while ((len = channel.read(buffer)) != -1) {
            byte[] bytes = new byte[len];
            // 切换至读模式
            buffer.flip();
            // 是否还有剩余数据
            while (buffer.hasRemaining()) {
                //byte b = buffer.get();
                // log.info("{}", b};
                buffer.get(bytes);
                log.info("{}", new String(bytes));
            }
            // 切换至写模式
            buffer.clear();

            // 把未读的数据往前移
           // buffer.compact();
        }
        channel.close();
    }
}
