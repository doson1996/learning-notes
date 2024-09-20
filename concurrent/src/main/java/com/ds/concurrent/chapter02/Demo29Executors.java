package com.ds.concurrent.chapter02;

import java.util.concurrent.Executors;

/**
 * @Author ds
 * @Date 2021/4/22 10:05
 * @Description
 */
public class Demo29Executors {

    public static void main(String[] args) {
        Executors.newFixedThreadPool(10);
        Executors.newSingleThreadExecutor();

        /**
         * CachedThreadPool 使用没有容量的 SynchronousQueue 作为线程池的工
         * 作队列，但 CachedThreadPool 的 maximumPool 是无界的。这意味着，如果主线程提交任
         * 务的速度高于 maximumPool 中线程处理任务的速度时， CachedThreadPool 会不断创建新
         * 线程。极端情况下， CachedThreadPool 会因为创建过多线程而耗尽 CPU 和内存资源
         */
        Executors.newCachedThreadPool();
        Executors.newScheduledThreadPool(1);
    }
}
