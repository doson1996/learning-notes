package com.ds.jvm.chapter01;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ds
 * @date 2021/6/21 23:18
 */
public class Demo17STW {
    public static void main(String[] args) {
        new ThreadA().start();
        new ThreadB().start();
    }

    public static class ThreadA extends Thread {
        List<byte[]> list = new ArrayList<>();

        @Override
        public void run() {
            try {
                while (true) {
                    for (int i = 0; i < 10000; i++) {
                        byte[] buff = new byte[1024];
                        list.add(buff);
                    }

                    if (list.size() > 10000) {
                        list.clear();
                        System.gc();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class ThreadB extends Thread {
        public static final long l = System.currentTimeMillis();

        @Override
        public void run() {
            try {
                while (true) {
                    long t = System.currentTimeMillis() - l;
                    System.out.println(t / 1000 + "." + t % 1000);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
