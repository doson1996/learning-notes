package com.ds.netty.chapter01;

import lombok.extern.slf4j.Slf4j;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author ds
 * @date 2023/4/20
 * @description
 */
@Slf4j
public class Demo02ByteBufferWriteRead {
    public static void main(String[] args) throws Exception {

        // 准备缓冲区 10字节
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{1, 2, 3});
        System.out.println("position: " + buffer.position() + " limit: " + buffer.limit());
        System.out.println(Arrays.toString(buffer.array()));

        // buffer [1,2,3]
        // 切换至读模式
        buffer.flip();

        byte v1 = buffer.get();
        System.out.println("v1 = " + v1);

        // 把未读的数据往前移 buffer [2,3,3]
        buffer.compact();
        System.out.println("------------------------------------------");
        System.out.println("position: " + buffer.position() + " limit: " + buffer.limit());
        System.out.println(Arrays.toString(buffer.array()));

        // buffer [2,3,3,4,5,6]
        buffer.put(new byte[]{4, 5, 6});

        System.out.println("------------------------------------------");
        System.out.println("position: " + buffer.position() + " limit: " + buffer.limit());
        System.out.println(Arrays.toString(buffer.array()));

        buffer.flip();
        byte v2 = buffer.get();
        System.out.println("v2 = " + v2);
    }
}
