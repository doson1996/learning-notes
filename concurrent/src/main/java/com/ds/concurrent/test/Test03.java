package com.ds.concurrent.test;

import java.util.concurrent.CountDownLatch;

/**
 * @author ds
 */
public class Test03 {

    private static final CountDownLatch LATCH = new CountDownLatch(5);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("----");
                LATCH.countDown();
            }).start();
        }
        System.out.println("-");
        LATCH.await();
        System.out.println("--");
    }

}
