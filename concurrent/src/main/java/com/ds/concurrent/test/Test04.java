package com.ds.concurrent.test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ds
 * @date 2026/3/26
 * @description 两个线程交叉打印0-10
 */
public class Test04 {

    public static void main(String[] args) {
//        reentrantLockCondition();
//        objectWaitNotify();


    }

    /**
     * 使用 Object wait notify实现
     */
    private static void objectWaitNotify() {
        Object lock = new Object();
        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + " i = " + i);
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        lock.notify();
                    }

                }
            }
        }).start();

        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    if (i % 2 == 1) {
                        System.out.println(Thread.currentThread().getName() + " i = " + i);
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        lock.notify();
                    }

                }
            }
        }).start();
    }

    /**
     * 使用 ReentrantLock + Condition 实现
     */
    private static void reentrantLockCondition() {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    if (i % 2 == 0) {
                        System.out.println(Thread.currentThread().getName() + " i = " + i);
                        condition.await();
                    } else {
                        condition.signal();
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < 10; i++) {
                    if (i % 2 == 1) {
                        System.out.println(Thread.currentThread().getName() + " i = " + i);
                        condition.await();
                    } else {
                        condition.signal();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }).start();
    }

}
