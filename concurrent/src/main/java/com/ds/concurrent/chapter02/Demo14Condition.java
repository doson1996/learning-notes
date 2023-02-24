package com.ds.concurrent.chapter02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author ds
 * @Date 2021/4/15 16:02
 * @Description
 *              一般都会将 Condition 对象作为成员变量。当调用 await()方法后，当前线程会释放锁并在此等待，
 *              而其他线程调用 Condition 对象的 signal()方法，通知当前线程后，当前线程才从 await()方法返回，并且在返回前已经获取了锁
 */
public class Demo14Condition {

    static Lock lock = new ReentrantLock();

    static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        BoundedQueue<String> bq = new BoundedQueue<>(10);

        new Thread(()->{
            //conditionWait();
            for (int j = 0; j < 10; j++) {
                bq.add(j + "");
               // System.out.println("add" + bq.toString());
            }

        }).start();

        new Thread(()->{
           // conditionSignal();
            for (int j = 0; j < 10; j++) {
                bq.remove();
                System.out.println(bq.toString());
            }
        }).start();
    }

    public static void conditionWait() {
        lock.lock();
        try {
            try {
                System.out.println("conditionWait start");
                condition.await();
                System.out.println("conditionWait end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            lock.unlock();
        }
    }

    public static void conditionSignal() {
        lock.lock();
        try {
            System.out.println("conditionSignal start");
            condition.signal();
            System.out.println("conditionSignal end");
        } finally {
            lock.unlock();
        }
    }

    /**
     *  通过一个有界队列的示例来深入了解 Condition 的使用方式。有界队列是一种特殊的队列，
     *  当队列为空时，队列的获取操作将会阻塞获取线程，直到队列中有新增元素，当队列已满时，
     *  队列的插入操作将会阻塞插入线程，直到队列出现“空位”
     */
    private static class BoundedQueue<T> {

        private Object[] items;
        // 添加的下标，删除的下标和数组当前数量
        private int addIndex, removeIndex, count;
        private Lock lock = new ReentrantLock();
        private Condition notEmpty = lock.newCondition();
        private Condition notFull = lock.newCondition();

        public BoundedQueue(int capacity) {
            items = new Object[capacity];
        }

        /**
         * 添加一个元素，如果数组满，则添加线程进入等待状态，直到有"空位"
         * @param item
         */
        public void add(T item) {
            lock.lock();
            try {
                while (count == items.length) {
                    try {
                        notFull.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                items[addIndex] = item;
                if (++addIndex == items.length) addIndex = 0;
                ++count;
                notEmpty.signal();
            } finally {
                lock.unlock();
            }
        }

        /**
         * 由头部删除一个元素，如果数组空，则删除线程进入等待状态，直到有新添加元素
         * @return
         */
        public T remove() {
            lock.lock();
            try {
                while (count == 0){
                    try {
                        notEmpty.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                Object o = items[removeIndex];
                items[removeIndex] = null;
                if (++removeIndex == items.length) removeIndex = 0;
                --count;
                notFull.signal();
                return (T) o;
            } finally {
                lock.unlock();
            }
        }

        @Override
        public String toString() {
            return "BoundedQueue{" +
                    "items=" + Arrays.toString(items) +
                    '}';
        }
    }

}
