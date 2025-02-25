package com.ds.concurrent.chapter04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.ds.concurrent.util.ThreadUtils;

/**
 * @author ds
 * @date 2023/5/14
 * @description
 */
public class Demo02Future {

    private static final ThreadPoolExecutor threadPool =
            new ThreadPoolExecutor(2, 3, 2, TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>(2),
                    new ThreadPoolExecutor.DiscardPolicy());

    private static volatile List<Future<Result>> futureList = new ArrayList<>();

    private static volatile boolean run = true;

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 5; i++) {
            Future<Result> future = threadPool.submit(new ProviderTask());
            futureList.add(future);
        }
        new Thread(new ConsumerTask()).start();
        threadPool.shutdown();
        boolean close = threadPool.awaitTermination(5, TimeUnit.SECONDS);
        if (close) {
            System.out.println("关闭线程池...");
            run = false;
        }
    }

    private static class ProviderTask implements Callable<Result> {
        @Override
        public Result call() throws Exception {
            int code = (int) (Math.random() * 10);
            System.out.println(Thread.currentThread().getName() + "执行任务..." + code);
            ThreadUtils.milliseconds(200);
            return new Result(code, "ok");
        }
    }

    private static class ConsumerTask implements Runnable {
        @Override
        public void run() {
            while (run) {
                for (Future<Result> future : futureList) {
                    Result result;
                    try {
                        result = future.get();
                        System.out.println(Thread.currentThread().getName() + "处理结果..." + result.code);
                        ThreadUtils.milliseconds(200);
                    } catch (Exception e) {
                        System.out.println("e = " + e);
                    }
                }
            }
        }
    }

    private static class Result {
        private int code;

        private String msg;

        public Result(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        @Override
        public String toString() {
            return "Result{" +
                    "code=" + code +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }

}
