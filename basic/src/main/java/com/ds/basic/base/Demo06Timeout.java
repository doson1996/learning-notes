package com.ds.basic.base;

import java.util.concurrent.TimeUnit;

/**
 * @author ds
 * @date 2023/5/14
 * @description
 */
public class Demo06Timeout {
    public static void main(String[] args) {
        new Thread(new Task()).start();
    }

    private static void start(long timeout) {
        TimeoutThread timeoutThread = new TimeoutThread(timeout);
        timeoutThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("执行任务...");
        timeoutThread.setCom();
    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            start(1001);
        }
    }

    private static class TimeoutThread extends Thread {

        private long timeout;

        private volatile int state;

        public TimeoutThread(long timeout) {
            this.timeout = timeout;
            this.setDaemon(true);
        }

        public void setCom() {
            // 执行完成
            state = 3;
        }

        @Override
        public void run() {
            long start = System.nanoTime();
            // 开始执行
            state = 1;
            while (System.nanoTime() - start < TimeUnit.MICROSECONDS.toNanos(timeout)) {
                // 执行中
                state = 2;
            }

            if (state < 3) {
                //执行超时
                state = 4;
                throw new RuntimeException("超时异常...");
            }
        }
    }

}
