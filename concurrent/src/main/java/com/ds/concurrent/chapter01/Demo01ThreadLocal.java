package com.ds.concurrent.chapter01;

/**
 * @Author ds
 * @Date 2021/3/10 9:44
 * @Description threadLocal
 */
public class Demo01ThreadLocal {

    public static void main(String[] args) {

        ThreadLocal threadLocal = new ThreadLocal();

        new Thread(new ThreadA(threadLocal)).start();
        new Thread(new ThreadB(threadLocal)).start();
    }

    private static class ThreadA implements Runnable{

        ThreadLocal threadLocal;

        public ThreadA(ThreadLocal threadLocal){
            this.threadLocal = threadLocal;
        }

        @Override
        public void run() {
            threadLocal.set("A");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThreadA==" + threadLocal.get());
        }
    }

    private static class ThreadB implements Runnable{

        ThreadLocal threadLocal;

        public ThreadB(ThreadLocal threadLocal){
            this.threadLocal = threadLocal;
        }

        @Override
        public void run() {
            threadLocal.set("B");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ThreadB==" + threadLocal.get());
        }
    }
}
