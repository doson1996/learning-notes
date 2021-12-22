package com.ds.concurrent.test;

/**
 * @author ds
 * @date 2021/7/1 0:00
 */
public class Test01 {

    public static int i = 0;

    public static final int SIZE = 10000;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int j = 0; j < SIZE; j++) {
                i++;
            }
        });

        Thread t2 = new Thread(() -> {
            for (int j = 0; j < SIZE; j++) {
                i++;
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
        System.out.println(i);
    }
}
