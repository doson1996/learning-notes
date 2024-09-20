package com.ds.concurrent.chapter02;

import com.ds.concurrent.util.SleepUtils;

/**
 * @Author ds
 * @Date 2021/4/2 11:18
 * @Description main 线程通过中断操作和 cancel()方法均可使 CountThread 得以
 * 终止。 这种通过标识位或者中断操作的方式能够使线程在终止时有机会去清理资源，而
 * 不是武断地将线程停止，因此这种终止线程的做法显得更加安全和优雅
 */
public class Demo07Shutdown {

    public static void main(String[] args) throws InterruptedException {

        Thread thread1 = new Thread(new Runner());
        thread1.start();
        /// 睡眠 1 秒， main 线程对 thread1 进行中断，使 thread1 能够感知中断而结束
        SleepUtils.seconds(1);
        thread1.interrupt();
        // thread1.join();

        Runner two = new Runner();
        Thread thread2 = new Thread(two);
        thread2.start();
        /// 睡眠 1 秒， main 线程对 thread2 进行中断，使 thread2 能够感知中断而结束
        SleepUtils.seconds(1);
        two.cancel();

    }

    private static class Runner implements Runnable {

        long i = 0;

        volatile boolean on = true;

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.println(i);
        }

        public void cancel() {
            this.on = false;
        }
    }

}

