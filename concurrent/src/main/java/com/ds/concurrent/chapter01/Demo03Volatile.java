package com.ds.concurrent.chapter01;


/**
 * @Author ds
 * @Date 2021/3/12 11:34
 * @Description volatile信号量   - 保证变量的内存可见性 - 禁止指令重排序
 */
public class Demo03Volatile {

    public static volatile int a = 0;

    public static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        new Thread(new ThreadA()).start();
        Thread.sleep(100);
        new Thread(new ThreadB()).start();
    }


    static class ThreadA implements Runnable {
        @Override
        public void run() {
            while (a < 10) {
                if (a % 2 == 0) {
                    System.out.println("A--" + a);
                    synchronized (lock) {
                        a = a + 1;
                    }
                }
            }
        }
    }

    static class ThreadB implements Runnable {

        @Override
        public void run() {
            while (a < 10) {
                if (a % 2 != 0) {
                    System.out.println("B--" + a);
                    synchronized (lock) {
                        a = a + 1;
                    }
                }
            }
        }
    }

}

