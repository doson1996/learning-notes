package com.ds.concurrent.chapter02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.locks.ReentrantLock;

import com.ds.concurrent.util.ThreadUtils;

/**
 * @Author ds
 * @Date 2021/4/7 10:48
 * @Description 公平性锁保证了锁的获取按照 FIFO 原则，而代价是进行大量的线程切换。
 * 非公平性锁虽然可能造成线程“饥饿”，但极少的线程切换，保证了其更大的吞吐量
 */
public class Demo12ReentrantLock {

    /**
     * 公平锁
     */
    static ReentrantLock1 fairLock = new ReentrantLock1(true);

    /**
     * 非公平锁
     */
    static ReentrantLock1 unfairLock = new ReentrantLock1(false);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Job(fairLock).start();
        }

        ThreadUtils.seconds(2);
        System.out.println("---------------------");

        for (int i = 0; i < 5; i++) {
            new Job(unfairLock).start();
        }

    }

    private static class Job extends Thread {

        ReentrantLock1 lock;

        public Job(ReentrantLock1 lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                ArrayList<Thread> queuedThreads = lock.getQueuedThreads();
                long[] ids = new long[queuedThreads.size()];
                for (int i = 0; i < queuedThreads.size(); i++) {
                    ids[i] = queuedThreads.get(i).getId();
                }
                System.out.println("lock - " + Thread.currentThread().getId() + Arrays.toString(ids));
            } finally {
                lock.unlock();
            }
        }
    }

    private static class ReentrantLock1 extends ReentrantLock {
        public ReentrantLock1(boolean fair) {
            super(fair);
        }

        @Override
        protected ArrayList<Thread> getQueuedThreads() {
            ArrayList<Thread> threads = new ArrayList<>(super.getQueuedThreads());
            Collections.reverse(threads);
            return threads;
        }
    }


}
