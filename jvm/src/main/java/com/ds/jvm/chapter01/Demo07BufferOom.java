package com.ds.jvm.chapter01;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author ds
 * @Date 2021/5/13 23:00
 * @Description 直接内存OOM
 */
public class Demo07BufferOom {

    public static final int BUFFER = 1 * 1024 *1024;

    public static void main(String[] args) {
        List<ByteBuffer> list = new LinkedList<>();

        int sum = 0;
        while (true) {
            try {
                sum++;
                ByteBuffer byteBuffer = ByteBuffer.allocateDirect(BUFFER);
                list.add(byteBuffer);

            } catch (Exception e) {
                //e.getStackTrace();
            } finally {
                System.out.println(sum);
            }

        }

    }
}
