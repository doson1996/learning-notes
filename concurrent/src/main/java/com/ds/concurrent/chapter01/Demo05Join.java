package com.ds.concurrent.chapter01;

/**
 * @Author ds
 * @Date 2021/3/12 14:37
 * @Description join
 * <p>
 * sleep                       wait
 * 1. 必须指定时间                     非必须
 * 2. 释放cpu资源，同时释放锁           释放cpu资源，不释放锁，容易造成死锁
 * 3. 不许放在同步代码块、同步方法中      任意位置
 */
public class Demo05Join {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ThreadA());
        thread.start();
        thread.join();
        System.out.println("主线程,不加join会被先打印出来");
    }

    private static class ThreadA implements Runnable {

        @Override
        public void run() {
            System.out.println("我是子线程,睡一秒");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("我是子线程,睡完一秒");
        }
    }
}
