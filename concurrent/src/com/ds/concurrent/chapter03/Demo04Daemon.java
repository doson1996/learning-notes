package com.ds.concurrent.chapter03;

/**
 * @author ds
 * @date 2021/12/28 21:48
 */
public class Demo04Daemon {
    public static void main(String[] args) {
        Thread thread = new Thread(new DaemonThread());
        thread.setDaemon(true);
        thread.start();
        new Thread(new MyThread()).start();
    }

    private static class DaemonThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                System.out.println("守护线程---");
            }
        }
    }

    private static class MyThread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("用户线程----------");
            }
        }
    }
}
