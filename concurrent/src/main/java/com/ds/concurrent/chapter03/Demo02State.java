package com.ds.concurrent.chapter03;

/**
 * @author ds
 * @date 2021/12/27 22:56
 */
public class Demo02State {
    public static void main(String[] args) {
        Thread thread = new Thread(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("run...");
        });

        System.out.println(thread.getState());

        thread.start();
        System.out.println(thread.getState());

        while (thread.getState() != Thread.State.TERMINATED) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(thread.getState());
        }

    }
}
