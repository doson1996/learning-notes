package com.ds.netty.chapter01;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author ds
 * @date 2023/4/25
 * @description
 */
public class Demo05ByteBufferString {
    public static void main(String[] args) {
        //String转ByteBuffer
        // 1.put
        ByteBuffer buffer1 = ByteBuffer.allocate(10);
        buffer1.put("hello".getBytes());
      //  BufferUtils.print(buffer1);

        // 2.Charset
        ByteBuffer buffer2 = StandardCharsets.UTF_8.encode("hello");
        BufferUtils.print(buffer2);

        // wrap
        ByteBuffer buffer3 = ByteBuffer.wrap("hello".getBytes());
        BufferUtils.print(buffer3);

        //ByteBuffer转String
        String str1 = StandardCharsets.UTF_8.decode(buffer2).toString();
        System.out.println("str1 = " + str1);

        // buffer1这种方式还未切换至读模式
        buffer1.flip();
        str1 = StandardCharsets.UTF_8.decode(buffer1).toString();
        System.out.println("str1 = " + str1);
    }
}
