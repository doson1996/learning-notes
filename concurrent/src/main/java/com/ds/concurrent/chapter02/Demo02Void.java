package com.ds.concurrent.chapter02;

import java.util.concurrent.TimeUnit;

/**
 * @Author ds
 * @Date 2021/3/30 11:05
 * @Description
 */
public class Demo02Void {

    public static void main(String[] args) {
        Demo02Void demo02 = new Demo02Void();
        System.out.println("--- 执行之前 ---");
        demo02.stop();
        System.out.println("--- 继续执行 ---");

    }

    public void stop() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //   new Thread(new ThreadA()).start();
        //   return 1;
    }

    private class ThreadA implements Runnable {

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThreadA--");
        }
    }
}
