package com.ds.concurrent.chapter02;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.ds.concurrent.threadpool.NameThreadFactory;

/**
 * @Author ds
 * @Date 2021/4/19 17:44
 * @Description ⚫ int availablePermits()：返回此信号量中当前可用的许可证数。
 * ⚫ int getQueueLength()：返回正在等待获取许可证的线程数。
 * ⚫ boolean hasQueuedThreads()：是否有线程正在等待获取许可证。
 * ⚫ void reducePermits（int reduction）：减少 reduction 个许可证，是个 protected 方法。
 */
public class Demo24Semaphore {
    private static Semaphore semaphore = new Semaphore(5);

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = buildThreadPoolExecutor();

        //    ExecutorService executor = Executors.newFixedThreadPool(30);

        for (int i = 0; i < 30; i++) {
            executor.execute(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + " --- " +
                            "可用的许可证数 - " + semaphore.availablePermits() +
                            "等待获取许可证的线程数 - " + semaphore.getQueueLength()); // + new Date().toLocaleString()
                    Thread.sleep(500);
                    semaphore.release();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        executor.awaitTermination(3L, TimeUnit.SECONDS);
    }

    private static ThreadPoolExecutor buildThreadPoolExecutor() {
        int corePoolSize = 30;
        int maximumPoolSize = 30;
        long keepAliveTime = 60L;
        TimeUnit unit = TimeUnit.MICROSECONDS;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>();
        ThreadFactory threadFactory = new NameThreadFactory("ds");
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();

        return new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                threadFactory,
                handler);
    }

}
