package com.ds.concurrent.chapter02.threadpool;

import java.util.concurrent.*;

/**
 * @Author ds
 * @Date 2021/4/21 16:17
 * @Description
 */
public class CustomizeThreadPool extends ThreadPoolExecutor {

    public CustomizeThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public CustomizeThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public CustomizeThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public CustomizeThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }

    /**
     * 任务执行前
     * @param t
     * @param r
     */
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
       // System.out.println("任务执行前");
        /**
         * 当创建过的最大线程池达到最大线程数时，改变核心线程数和最大线程数
         */
        if (this.getLargestPoolSize() == this.getMaximumPoolSize() - 1) {
            int maximumPoolSize = this.getMaximumPoolSize();
            this.setMaximumPoolSize(maximumPoolSize * 2);
            this.setCorePoolSize(maximumPoolSize);
        }

    }

    /**
     * 任务执行后
     * @param r
     * @param t
     */
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
       // System.out.println("任务执行后");
    }

    /**
     * 线程池关闭前
     */
    @Override
    protected void terminated() {
       // System.out.println("线程池关闭前");
    }
}
