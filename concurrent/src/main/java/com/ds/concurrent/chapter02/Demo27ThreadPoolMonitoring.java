package com.ds.concurrent.chapter02;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author ds
 * @Date 2021/4/21 16:02
 * @Description 线程池的监控
 */
public class Demo27ThreadPoolMonitoring {

    private static final int CORE_POOL_SIZE = 2;

    private static final int MAX_POOL_SIZE = 5;

    private static final int QUEUE_CAPACITY = 10;

    private static final Long KEEP_ALIVE_TIME = 10L;

    private static AtomicInteger ai = new AtomicInteger(1);

    private static final ThreadPoolExecutor threadPool = new CustomizeThreadPool(  // new CustomizeThreadPool(  new ThreadPoolExecutor(
            CORE_POOL_SIZE,
            MAX_POOL_SIZE,
            KEEP_ALIVE_TIME,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>(QUEUE_CAPACITY),
            new CustomizePolicy());

    public static void main(String[] args) {


        for (int i = 0; i < 40; i++) {
            //有序启动线程
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            threadPool.execute(() -> {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                /**
                 * {@link CustomizePolicy} ->beforeExecute
                 * 当创建过的最大线程池达到最大线程数时，改变核心线程数和最大线程数
                 *
                 */
                /*if (threadPool.getLargestPoolSize() == threadPool.getMaximumPoolSize() - 1) {
                    int maximumPoolSize = threadPool.getMaximumPoolSize();
                    threadPool.setMaximumPoolSize(maximumPoolSize * 2);
                    threadPool.setCorePoolSize(maximumPoolSize);
                }*/

                System.out.println("task--" + ai.getAndIncrement() +
                                    "-> 线程池的线程数量:" + threadPool.getPoolSize() +
                                    " - 线程池核心线程数:" + threadPool.getCorePoolSize() +
                                    " - 线程池最大线程数:" + threadPool.getMaximumPoolSize() +
                                    " - 线程池活动线程数:" + threadPool.getActiveCount() +
                                    " - 线程池创建过最大线程数:" + threadPool.getLargestPoolSize() +
                                    " - 线程池需要执行的任务数:" + threadPool.getTaskCount() +
                                    " - 线程池已完成的任务数:" + threadPool.getCompletedTaskCount() +
                                    " - 线程池队列大小:" + (threadPool.getQueue().size() + threadPool.getQueue().remainingCapacity()) +
                                    " - 当前排队线程数: " + threadPool.getQueue().size() +
                                    " - 队列剩余大小: " + threadPool.getQueue().remainingCapacity());
            });

        }

        threadPool.shutdown();
    }

}
