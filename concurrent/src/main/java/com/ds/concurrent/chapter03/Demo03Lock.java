package com.ds.concurrent.chapter03;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ds
 * @date 2021/12/30 23:05
 */
public class Demo03Lock {
    public static void main(String[] args) {
        Buyer buyer = new Buyer();
        new Thread(buyer).start();
        new Thread(buyer).start();
        new Thread(buyer).start();
    }
}


class Buyer implements Runnable {

    final ReentrantLock lock = new ReentrantLock();

    int ticket = 10;

    @Override
    public void run() {
        buy();
    }

    public void buy() {
        lock.lock();
        try {
            while (ticket > 0) {
                ticket--;
                System.out.println(Thread.currentThread().getName() + "  " + ticket);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}