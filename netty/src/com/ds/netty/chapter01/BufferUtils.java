package com.ds.netty.chapter01;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author ds
 * @date 2023/4/25
 * @description
 */
public class BufferUtils {

    public static void print(ByteBuffer buffer) {
        buffer.mark();
        byte[] bytes = new byte[buffer.limit()];
        buffer.get(bytes);
        String value = new String(bytes);
        buffer.reset();
        System.out.println("------------------------------------------");
        System.out.println("position: " + buffer.position() +
                " limit: " + buffer.limit() +
                " cap: " + buffer.capacity() + "\n" +
                "value: " + value);
        System.out.println(Arrays.toString(buffer.array()));
    }

}
