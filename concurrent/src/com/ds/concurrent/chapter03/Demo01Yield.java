package com.ds.concurrent.chapter03;

/**
 * 线程礼让，不一定成功
 * @author ds
 * @date 2021/12/23 22:02
 */
public class Demo01Yield {
    public static void main(String[] args) {
        YieldThread a = new YieldThread();
        YieldThread b = new YieldThread();

        new Thread(a, "a").start();
        new Thread(b, "b").start();
    }

    private static class YieldThread implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()  + "--> start");
            if ("a".equals(Thread.currentThread().getName()))
                Thread.yield();
            System.out.println(Thread.currentThread().getName()  + "--> end");
        }
    }
}
