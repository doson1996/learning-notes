package com.ds.concurrent.chapter01;

/**
 * @Author ds
 * @Date 2021/3/25 15:18
 * @Description
 */
public class Demo06Volatile {

    public static volatile boolean flag = true;

    public static void main(String[] args) {

        ThreadA a = new ThreadA();
        ThreadB b = new ThreadB();

        Thread threadA = new Thread(a);
        Thread threadB = new Thread(b);

        threadA.start();
        threadB.start();

    }

    private static class ThreadA implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = false;
        }
    }

    private static class ThreadB implements Runnable{

        @Override
        public void run() {
            while (flag){
                System.out.println("线程B获等待flag改变");
            }
        }
    }
}
