package com.ds.netty.chapter01nio;

import java.nio.ByteBuffer;

/**
 * @author ds
 * @date 2023/4/24
 * @description
 */
public class Demo03ByteBufferAllocate {
    public static void main(String[] args) {
        ByteBuffer allocate = ByteBuffer.allocate(16);
        System.out.println("allocate = " + allocate.getClass());
        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(16);
        System.out.println("allocateDirect = " + allocateDirect.getClass());
        // allocate = class java.nio.HeapByteBuffer          java堆内存，读写效率低，受gc影响
        // allocateDirect = class java.nio.DirectByteBuffer  直接内存，读写效率高(少拷贝一次)，不受gc影响，分配效率低(会调用系统函数)

    }
}
