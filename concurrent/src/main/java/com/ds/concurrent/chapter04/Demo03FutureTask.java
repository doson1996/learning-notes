package com.ds.concurrent.chapter04;

import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ds
 * @date 2023/5/14
 * @description FutureTask
 * 1.无法获取任务执行进度：FutureTask无法直接获取任务的执行进度，只能获取任务的执行结果。
 * 2.无法动态取消任务：一旦FutureTask进入运行状态，就无法再取消任务，只能等待任务执行完成。
 * 3.单次使用：每个FutureTask只能执行一次，如果需要再次执行，需要创建新的FutureTask对象。
 */
public class Demo03FutureTask {

    private static final ThreadPoolExecutor threadPool =
            new ThreadPoolExecutor(2, 3, 2, TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(2),
                    new ThreadPoolExecutor.DiscardPolicy());

    public static void main(String[] args) throws Exception {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println("执行任务...");
            Thread.sleep(2000);
            return "1";
        });

        for (int i = 0; i < 5; i++) {
            threadPool.submit(futureTask);
        }

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(()->{
                String s = null;
                try {
                    s = futureTask.get(finalI * 1000, TimeUnit.MILLISECONDS);
                } catch (Exception e) {
                    System.out.println(finalI + "异常");
                    throw new RuntimeException(e);
                }
                System.out.println("s" + finalI + " = " + s);
            }).start();
        }




        threadPool.shutdown();
    }
}
