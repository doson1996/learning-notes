package com.ds.netty.chapter02netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.CompositeByteBuf;

/**
 * @author ds
 * @date 2023/6/14
 * @description
 */
public class Demo04Composite {
    public static void main(String[] args) {
        ByteBuf buf1 = ByteBufAllocator.DEFAULT.buffer();
        buf1.retain();
        buf1.writeBytes(new byte[]{1, 2, 3, 4, 5});
        ByteBufUtils.log(buf1);

        ByteBuf buf2 = ByteBufAllocator.DEFAULT.buffer();
        buf2.retain();
        buf2.writeBytes(new byte[]{6, 7, 8, 9, 10});
        ByteBufUtils.log(buf2);

        CompositeByteBuf compositeBuffer = ByteBufAllocator.DEFAULT.compositeBuffer();
        compositeBuffer.retain();
        compositeBuffer.addComponents(true, buf1, buf2);
        ByteBufUtils.log(compositeBuffer);
    }
}
