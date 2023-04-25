package com.ds.netty.chapter01;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author ds
 * @date 2023/4/25
 * @description 集中写
 */
public class Demo06GatheringWrites {
    public static void main(String[] args) throws Exception {
        ByteBuffer buffer1 = ByteBuffer.allocate(3);
        buffer1.put("abc".getBytes());
        buffer1.flip();
        ByteBuffer buffer2 = ByteBuffer.allocate(3);
        buffer2.put("def".getBytes());
        buffer2.flip();
        ByteBuffer buffer3 = ByteBuffer.allocate(6);
        buffer3.put("张三".getBytes());
        buffer3.flip();
        try (FileChannel channel = new RandomAccessFile("rw.txt", "rw").getChannel()) {
            channel.write(new ByteBuffer[]{buffer1, buffer2, buffer3});
        } catch (Exception e) {

        }
    }
}
