package com.ds.concurrent.threadpool;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @Author ds
 * @Date 2021/3/29 17:20
 * @Description 动态线程池参数测试
 */
public class ThreadPoolChangeDemo {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = buildThreadPoolExecutor();
        dynamicModifyExecutor(executor);
        executor.shutdown();
    }

    /**
     * 自定义线程池
     * @return
     */
    private static ThreadPoolExecutor buildThreadPoolExecutor(){
        int corePoolSize = 2;
        int maximumPoolSize = 5;
        long keepAliveTime = 60L;
        TimeUnit unit = TimeUnit.MICROSECONDS;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(1);
        ThreadFactory threadFactory = new NameThreadFactory("ds");
        RejectedExecutionHandler handler = new ThreadPoolExecutor.DiscardPolicy();

        return new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                unit,
                workQueue,
                threadFactory,
                handler);

    }

    /**
     * 提交任务给线程池，并且改变线程池参数
     */
    private static void dynamicModifyExecutor(ThreadPoolExecutor executor) throws InterruptedException {


        for (int i = 0; i < 20; i++) {
            executor.submit(()->{
                threadPoolStatus(executor, "创建任务");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        /*threadPoolStatus(executor, "改变之前");
        executor.setCorePoolSize(10);
        executor.setMaximumPoolSize(10);
        threadPoolStatus(executor, "改变之后");*/
        Thread.currentThread().join();

    }

    /**
     * 线程池状态
     * @param executor
     * @param name
     */
    private static void threadPoolStatus(ThreadPoolExecutor executor, String name){

        LinkedBlockingQueue<Runnable> queue = (LinkedBlockingQueue) executor.getQueue();
        System.out.println(new Date().toLocaleString() + Thread.currentThread().getName() + "-" + name + "-: " +
                "核心线程池数: " + executor.getCorePoolSize() +
                " 活动线程数: " + executor.getActiveCount() +
                " 最大线程数: " + executor.getMaximumPoolSize() +
                " 线程活跃度: " + (divide(executor.getActiveCount(), executor.getMaximumPoolSize())) +
                " 完成任务数: " + executor.getCompletedTaskCount() +
                " 队列大小: " + (queue.size() + queue.remainingCapacity()) +
                " 当前排队线程数: " + queue.size() +
                " 队列剩余大小: " + queue.remainingCapacity() +
                " 队列使用度: " + divide(queue.size(),(queue.size() + queue.remainingCapacity()))
                );
    }

    private static String divide(int n1,int n2){
        return String.format("%1.2f%%",Double.parseDouble(n1 + "") / Double.parseDouble(n2 + "") * 100);
    }
}
