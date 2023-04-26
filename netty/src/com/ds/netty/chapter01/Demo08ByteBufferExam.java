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
        byte[] array = source.array();
        array = Arrays.copyOfRange(array, 0, source.limit());
        String str = new String(array);
        if (str.endsWith("\n")) {
            String[] data = str.split("\n");
            for (int i = 0; i < data.length; i++) {
                String line = data[i];
                System.out.println("line = " + line);
                ByteBuffer target = ByteBuffer.allocate(line.length());
                byte[] bytes = new byte[line.length()];
                if (i != 0) {
                    // 把 \n position更新掉
                    source.get();
                    source.get(bytes);
                } else {
                    source.get(bytes);
                }
                target.put(bytes);

                target.flip();
                BufferUtils.print(target);
            }
        } else {
            int index = str.lastIndexOf("\n");
            // 完整消息
            String str1 = str.substring(0, index);
            String[] data = str1.split("\n");
            for (int i = 0; i < data.length; i++) {
                String line = data[i];
                System.out.println("line = " + line);
                ByteBuffer target = ByteBuffer.allocate(line.length());
                byte[] bytes = new byte[line.length()];
                if (i != 0) {
                    // 把 \n position更新掉
                    source.get();
                    source.get(bytes);
                } else {
                    source.get(bytes);
                }
                target.put(bytes);

                target.flip();
                BufferUtils.print(target);
            }
        }
        source.compact();
    }

    public static void spit1(ByteBuffer source) {
        source.flip();
      //  BufferUtils.print(source);
        for (int i = 0; i < source.limit(); i++) {
            // get(i) 不会更新position
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
