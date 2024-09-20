package com.ds.concurrent.chapter01;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author ds
 * @Date 2021/3/26 10:09
 * @Description AQS (AbstractQueuedSynchronizer 的简称，即 抽象队列同步器 )
 */
public class Demo09Aqs {

    public static int sum = 0;

    /**
     * 线程数组数量
     */
    public static final int COUNT = 1000;

    static final ReentrantLock lock = new ReentrantLock();

    public static CountDownLatch latch = new CountDownLatch(COUNT);

    public static void main(String[] args) throws InterruptedException {

        Thread[] threads = new Thread[COUNT];

        for (int j = 0; j < COUNT; j++) {
            Thread thread = new Thread(new MyThread());
            threads[j] = thread;
        }
        for (Thread thread : threads) {
            thread.start();
        }

        //阻塞当前线程，直到计数器的值为0
        latch.await();

        System.out.println(sum);
    }

    private static class MyThread implements Runnable {

        @Override
        public void run() {
            for (int j = 0; j < COUNT; j++) {
                lock.lock();
                try {
                    sum++;
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }

            }
            //当前线程调用此方法，则计数减一
            latch.countDown();
        }
    }
}
