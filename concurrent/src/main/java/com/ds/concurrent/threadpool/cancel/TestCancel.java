package com.ds.concurrent.threadpool.cancel;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ds
 * @date 2024/6/25
 * @description
 */
public class TestCancel {

    private static final int CORE_POOL_SIZE = 1;
    private static final int MAX_POOL_SIZE = 1;
    private static final int QUEUE_CAPACITY = 1;
    private static final Long KEEP_ALIVE_TIME = 10L;

    private static final MyThreadFactory threadFactory = new MyThreadFactory("ds");
    private static final RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();

    //通过ThreadPoolExecutor构造函数自定义参数创建
    private static final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(QUEUE_CAPACITY),
            threadFactory,
            handler);

    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
//            cancel();
//            if (i % 2 == 1) {
//                cancel();
//            }
            Task task = new Task(i + "");
            threadPool.execute(task);
            int activeCount = threadPool.getActiveCount();
            System.out.println("activeCount = " + activeCount);
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        cancel();
        int activeCount = threadPool.getActiveCount();
        System.out.println("activeCount = " + activeCount);


    }

    private static void cancel() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        MyThreadFactory myThreadFactory = (MyThreadFactory) threadPool.getThreadFactory();
        List<Thread> threadList = myThreadFactory.getThreadList();
        for (Thread thread : threadList) {
//            System.out.println("interrupt");
            thread.interrupt();
//            thread.stop();
        }
    }

}
