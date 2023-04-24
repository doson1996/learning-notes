package com.ds.netty.chapter01;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author ds
 * @date 2023/4/24
 * @description
 */
public class Demo04ByteBufferRead {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(new byte[]{'a', 'b', 'c', 'd'});
        buffer.flip();

        byte[] bytes = new byte[2];
        buffer.get(bytes);
        System.out.println("bytes = " + Arrays.toString(bytes));

        buffer.get(bytes);
        System.out.println("bytes = " + Arrays.toString(bytes));

        // 重头开始读
        buffer.rewind();
        buffer.get(bytes);
        System.out.println("bytes = " + Arrays.toString(bytes));

        // mark & reset (循环读取数据)
        // mark 标记当前position的位置
        buffer.mark();
        buffer.get(bytes);
        System.out.println("bytes = " + Arrays.toString(bytes));

        // reset 将当前position重置到mark的位置
        buffer.reset();
        buffer.get(bytes);
        System.out.println("bytes = " + Arrays.toString(bytes));

        // buffer.get(i); 不会更新position
        byte v = buffer.get(0);
        System.out.println("v = " + v);
    }
}
