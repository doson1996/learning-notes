package com.ds.jvm.chapter01;

import com.ds.jvm.classloader.B;

import java.nio.ByteBuffer;
import java.util.Scanner;

/**
 * @Author ds
 * @Date 2021/5/9 9:57
 * @Description
 */
public class Demo07BufferTest {

    public static final int BUFFER = 1 * 1024 *1024;

    public static void main(String[] args) {
        //直接分配本地内存空间
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(BUFFER);
        System.out.println("直接内存分配完毕");

        Scanner scanner = new Scanner(System.in);
        scanner.next();

        byteBuffer = null;
        System.gc();
        System.out.println("直接内存释放完毕");

    }
}
