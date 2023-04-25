package com.ds.netty.chapter01;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * @author ds
 * @date 2023/4/25
 * @description
 */
public class Demo08ByteBufferExam {
    public static void main(String[] args) {
        ByteBuffer source = ByteBuffer.allocate(16);
        source.put("abc\ndefg\nhi".getBytes());
        spit(source);
        source.put("jk\n".getBytes());
        spit(source);
    }

    public static void spit(ByteBuffer source) {
        source.flip();
      //  BufferUtils.print(source);
        for (int i = 0; i < source.limit(); i++) {
            if (source.get(i) == '\n') {
                // source开始读的位置
                int len = i + 1 - source.position();
                ByteBuffer target = ByteBuffer.allocate(len);
                for (int j = 0; j < len; j++) {
                    target.put(source.get());
                }
                target.flip();
                BufferUtils.print(target);
            }
        }

        source.compact();
    }

}
