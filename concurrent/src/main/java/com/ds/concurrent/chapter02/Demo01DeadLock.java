package com.ds.concurrent.chapter02;

/**
 * @Author ds
 * @Date 2021/3/29 17:01
 * @Description
 */
public class Demo01DeadLock {

    private static final String A = "A";

    private static final String B = "B";

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (A){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B){
                    System.out.println(1);
                }

            }

        }).start();

        new Thread(() -> {
            synchronized (B){
                synchronized (A){
                    System.out.println(2);
                }

            }

        }).start();
    }
}
