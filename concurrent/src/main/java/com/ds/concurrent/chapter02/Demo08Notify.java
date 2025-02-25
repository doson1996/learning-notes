package com.ds.concurrent.chapter02;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ds.concurrent.util.SleepUtils;

/**
 * @Author ds
 * @Date 2021/4/2 11:39
 * @Description
 */
public class Demo08Notify {

    static boolean flag = true;

    static final Object lock = new Object();

    public static void main(String[] args) {

        Thread wait = new Thread(new Wait());
        wait.start();

        Thread notify = new Thread(new Notify());
        notify.start();

    }

    public static String getDate() {
        return new SimpleDateFormat(" HH: mm: ss ").format(new Date());
    }

    static class Wait implements Runnable {
        @Override
        public void run() {
            // 加锁，拥有 lock 的 Monitor
            synchronized (lock) {
                // 当条件不满足时，继续 wait，同时释放了 lock 的锁
                while (flag) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " flag is true. wait@" + getDate());
                        lock.wait();
                    } catch (InterruptedException e) {
                    }
                }
                // 条件满足时，完成工作
                System.out.println(Thread.currentThread().getName() + " flag is false. running@" + getDate());
            }
        }
    }

    static class Notify implements Runnable {
        @Override
        public void run() {
            // 加锁，拥有 lock 的 Monitor
            synchronized (lock) {
                // 获取 lock 的锁，然后进行通知，通知时不会释放 lock 的锁，
                // 直到当前线程释放了 lock 后， WaitThread 才能从 wait 方法中返回
                System.out.println(Thread.currentThread().getName() + " hold lock. notify@" + getDate());
                lock.notifyAll();
                flag = false;
                SleepUtils.seconds(5);
            }
            // 再次加锁
            synchronized (lock) {
                System.out.println(Thread.currentThread().getName() + " hold lock again. sleep@" + getDate());
                SleepUtils.seconds(5);
            }
        }
    }
}
