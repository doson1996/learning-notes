package com.ds.concurrent.threadpool;

import java.time.Instant;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author ds
 * @Date 2021/4/8 16:24
 * @Description
 *      一、线程池提交任务
 *       executor.execute() {@link ThreadPoolExecutor#execute(Runnable)}  判断是添加线程还是把任务添加到队列
 *        int c = ctl.get();
 *       // 1.如果线程池中的线程数量小于corePoolSize，则优先创建线程，若创建成功则返回，否则进入下一步
 *         if (workerCountOf(c) < corePoolSize) {
 *             if (addWorker(command, true))
 *                 return;
 *             c = ctl.get();
 *         }
 *        // 2. 如果线程池中的线程数量大于等于corePoolSize，则尝试将任务放入workQueue中
 *         if (isRunning(c) && workQueue.offer(command)) {
 *             int recheck = ctl.get();
 *             // 如果线程池已经关闭，需要将刚才放入队列的任务移除并拒绝
 *             if (!isRunning(recheck) && remove(command))
 *                 reject(command);
 *             // 如果没有工作线程，添加一个空任务 Worker，从队列中获取并执行任务
 *             else if (workerCountOf(recheck) == 0)
 *                 addWorker(null, false);
 *         }
 *        // 3. 如果workQueue已满,则创建非核心线程
 *         else if (!addWorker(command, false))
 *             reject(command);
 *
 *       二、添加worker                                                 添加线程后启动线程 runWorker，从队列中获取并执行任务
 *       addWorker {@link ThreadPoolExecutor#addWorker(Runnable, boolean)}
 *
 */
public class ThreadPoolDemo {

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 10;
    private static final int QUEUE_CAPACITY = 100;
    private static final Long KEEP_ALIVE_TIME = 1L;

    public static void main(String[] args) {

        //使用阿里巴巴推荐的创建线程池的方式
        //通过ThreadPoolExecutor构造函数自定义参数创建
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                CORE_POOL_SIZE,
                MAX_POOL_SIZE,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(QUEUE_CAPACITY),
                new ThreadPoolExecutor.CallerRunsPolicy());


        for (int i = 0; i < 10; i++) {
            executor.execute(() -> {
//                throw new RuntimeException("");
//                try {
//                    Thread.sleep(2000);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//                System.out.println("CurrentThread name:" + Thread.currentThread().getName() + "date：" + Instant.now());
            });
        }
        //终止线程池
        executor.shutdown();
        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished all threads");
    }

    private static int getScore() {
        return 1;
    }
}
