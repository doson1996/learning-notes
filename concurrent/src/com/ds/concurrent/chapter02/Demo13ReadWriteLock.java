package com.ds.concurrent.chapter02;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author ds
 * @Date 2021/4/15 15:13
 * @Description 读写锁
 *                  cache 组合一个非线程安全的 HashMap 作为缓存的实现，同时使用读
 *                  写锁的读锁和写锁来保证 cache 是线程安全的。在读操作 get(String key)方法中，需要获
 *                  取读锁，这使得并发访问该方法时不会被阻塞。写操作 put(String key,Object value)方法
 *                  和 clear()方法，在更新 HashMap 时必须提前获取写锁，当获取写锁后，其他线程对于读
 *                  锁和写锁的获取均被阻塞，而只有写锁被释放之后，其他读写操作才能继续。 Cache 使
 *                  用读写锁提升读操作的并发性，也保证每次写操作对所有的读写操作的可见性，同时简化了编程方式。
 */
public class Demo13ReadWriteLock {

    static Map<String,Object> cache = new HashMap<>();

    static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    static Lock r = rwl.readLock();

    static Lock w = rwl.writeLock();

    public static void main(String[] args) {
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                cache.put(i + "", "v" + i);
                System.out.println("1写入v" + i);
            }

        }).start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                Object v = cache.get(i + "");
                System.out.println("读取到" + v);
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                cache.put(i + "", "v" + i);
                System.out.println("2写入v" + i);
            }

        }).start();

    }

    public static Object get(String key) {
        r.lock();
        try {
            return cache.get(key);
        } finally {
            r.unlock();
        }
    }

    public static Object put(String key, Object value) {
        w.lock();
        try {
            return cache.put(key, value);
        } finally {
            w.unlock();
        }
    }

    public static void clear() {
        w.lock();
        try {
            cache.clear();
        } finally {
            w.unlock();
        }
    }


}
