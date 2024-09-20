package com.ds.concurrent.chapter02;

import com.ds.concurrent.util.ThreadUtils;

/**
 * @Author ds
 * @Date 2021/4/6 10:57
 * @Description
 */
public class Demo09Join {

    public static void main(String[] args) {

        Thread previous = Thread.currentThread();

        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new MyThread(previous), i + "");
            thread.start();
            previous = thread;
        }

        ThreadUtils.seconds(3);
        System.out.println(Thread.currentThread().getName() + " terminate");
    }

    private static class MyThread implements Runnable {

        private Thread thread;

        public MyThread(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " terminate");
        }
    }
}
