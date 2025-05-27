package com.ds.zk.lock;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * curator 框架实现zk分布式锁
 *
 * @author ds
 */
public class CuratorTest {

    public static final String LOCK_PATH = "/locks/prod1";

    private static final String ZK_HOST = "localhost:2181";

    public static void main(String[] args) {

        InterProcessMutex lock1 = new InterProcessMutex(getClient(), LOCK_PATH);

        InterProcessMutex lock2 = new InterProcessMutex(getClient(), LOCK_PATH);

        new Thread(() -> {
            try {
                lock1.acquire();
                System.out.println("线程1 加锁一次...");
                lock1.acquire();
                System.out.println("线程1 加锁两次...");
                Thread.sleep(3000);
                lock1.release();
                System.out.println("线程1 释放一次...");
                lock1.release();
                System.out.println("线程1 释放两次...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                lock2.acquire();
                System.out.println("线程2 加锁一次...");
                lock2.acquire();
                System.out.println("线程2 加锁两次...");
                Thread.sleep(3000);
                lock2.release();
                System.out.println("线程2 释放一次...");
                lock2.release();
                System.out.println("线程2 释放两次...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }

    private static CuratorFramework getClient() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(ZK_HOST, retryPolicy);

        client.start();
        return client;
    }

}
