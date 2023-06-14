package com.ds.netty.chapter02netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author ds
 * @date 2023/6/14
 * @description
 */
public class Demo03Slice {
    public static void main(String[] args) {
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer(10);
        buffer.writeBytes(new byte[]{'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'});
        ByteBufUtils.log(buffer);

        // 切片不会发生数据复制
        ByteBuf slice1 = buffer.slice(0, 5);
        // 计数加1
        slice1.retain();
        ByteBufUtils.log(slice1);
        ByteBuf slice2 = buffer.slice(5, 5);
        slice2.retain();
        ByteBufUtils.log(slice2);

        // 计数减1
        buffer.release();
        System.out.println("set-----------------------------------------------------------------------------");
        slice1.setByte(0, 'b');
        ByteBufUtils.log(slice1);
        ByteBufUtils.log(buffer);
    }
}
