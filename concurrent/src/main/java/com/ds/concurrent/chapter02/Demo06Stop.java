package com.ds.concurrent.chapter02;

import com.ds.concurrent.util.SleepUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author ds
 * @Date 2021/4/2 11:05
 * @Description 不建议使用的原因主要有：以 suspend()方法为例，在调用后，线程不会释放已经占
 * 有的资源（比如锁），而是占有着资源进入睡眠状态，这样容易引发死锁问题。同样，
 * stop()方法在终结一个线程时不会保证线程的资源正常释放，通常是没有给予线程完成资
 * 源释放工作的机会，因此会导致程序可能工作在不确定状态下。
 */
public class Demo06Stop {

    private static final Object lock = new Object();

    public static void main(String[] args) {

        Thread printThread = new Thread(new Runner(), "printThread ");
        printThread.setDaemon(true);
        printThread.start();
        SleepUtils.seconds(3);

        // Thread printThread1 = new Thread(new Runner1(),"printThread1 ");
        // printThread1.start();

        //暂停
        printThread.suspend();
        SleepUtils.seconds(3);

        //恢复
        printThread.resume();
        SleepUtils.seconds(3);

        //停止
        printThread.stop();

    }

    private static class Runner implements Runnable {

        @Override
        public void run() {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

            while (true) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + sdf.format(new Date()));
                    SleepUtils.seconds(1);
                }
            }
        }
    }

    private static class Runner1 implements Runnable {

        @Override
        public void run() {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

            while (true) {
                synchronized (lock) {
                    System.out.println(Thread.currentThread().getName() + sdf.format(new Date()));
                    SleepUtils.seconds(1);
                }
            }
        }
    }
}
