package com.ds.netty.chapter02netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author ds
 * @date 2023/6/14
 * @description
 */
public class Demo01ByteBuf {
    public static void main(String[] args) {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        System.out.println(buffer.getClass());
        ByteBuf heapBuffer = ByteBufAllocator.DEFAULT.heapBuffer();
        System.out.println(heapBuffer.getClass());

        ByteBufUtils.log(buffer);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 300; i++) {
            sb.append("a");
        }
        buffer.writeBytes(sb.toString().getBytes());
        ByteBufUtils.log(buffer); // ByteBuf会自动扩容
    }
}
