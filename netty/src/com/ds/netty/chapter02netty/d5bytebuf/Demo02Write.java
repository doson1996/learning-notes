package com.ds.netty.chapter02netty.d5bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author ds
 * @date 2023/6/14
 * @description
 */
public class Demo02Write {
    public static void main(String[] args) {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(10);
        buffer.writeBytes(new byte[]{1, 2, 3, 4});
        ByteBufUtils.log(buffer);

        buffer.writeInt(5);
        ByteBufUtils.log(buffer);

        buffer.writeIntLE(6);
        ByteBufUtils.log(buffer);
    }
}
