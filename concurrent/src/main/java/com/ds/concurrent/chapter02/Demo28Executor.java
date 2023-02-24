package com.ds.concurrent.chapter02;

import java.util.concurrent.*;

/**
 * @Author ds
 * @Date 2021/4/22 9:32
 * @Description
 */
public class Demo28Executor {

    public static void main(String[] args) throws Exception {
        ExecutorService executor = new ScheduledThreadPoolExecutor(2);

        Callable<String> a = Executors.callable(new Task(), "a");
        Future<String> submit = executor.submit(a);
        submit.cancel(true);
     //   System.out.println("submit.get() = " + submit.get());

        executor.shutdown();
    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("task");
        }
    }
}
