package com.ds.concurrent.test;

import com.ds.concurrent.util.SleepUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author ds
 * @date 2021/12/7 22:12
 */
public class Test02 {
    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(new ThreadA("a"));
        Thread b = new Thread(new ThreadA("b"));
        Thread c = new Thread(new ThreadA("c"));

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 10; i++) {
            executorService.submit(a);
            executorService.submit(b);
            executorService.submit(c);
        }



        executorService.shutdown();

    }

    private static class ThreadA implements Runnable {

        private String msg;

        public ThreadA(String msg) {
            this.msg = msg;
        }

        @Override
        public void run() {
            System.out.print(this.msg);
        }
    }
}

