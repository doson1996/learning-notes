package com.ds.netty.chapter01;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author ds
 * @date 2023/4/25
 * @description 分散读
 */
public class Demo07ScatteringReads {
    public static void main(String[] args) {
        ByteBuffer buffer1 = ByteBuffer.allocate(3);
        buffer1.put("abc".getBytes());
        buffer1.clear();
        ByteBuffer buffer2 = ByteBuffer.allocate(3);
        buffer2.put("def".getBytes());
        buffer2.clear();
        ByteBuffer buffer3 = ByteBuffer.allocate(6);
        buffer3.put("张三".getBytes());
        buffer3.clear();

        try (FileChannel channel = new RandomAccessFile("rw.txt", "r").getChannel()) {
            channel.read(new ByteBuffer[]{buffer1, buffer2, buffer3});
            buffer1.flip();
            BufferUtils.print(buffer1);
            buffer2.flip();
            BufferUtils.print(buffer2);
            buffer3.flip();
            BufferUtils.print(buffer3);
        } catch (Exception e) {

        }
    }
}
