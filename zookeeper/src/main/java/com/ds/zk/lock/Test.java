package com.ds.zk.lock;

/**
 * zk分布式锁测试
 *
 * @author ds
 */
public class Test {

    public static final String RESOURCE = "product:1001";

    public static void main(String[] args) throws Exception {
        final ZkLock zkLock1 = new ZkLock();
        final ZkLock zkLock2 = new ZkLock();

        new Thread(() -> {
            if (zkLock1.lock(RESOURCE)) {
                System.out.println("线程1 加锁成功...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                zkLock1.unlock();
                System.out.println("线程1 释放锁...");
            } else {
                System.out.println("线程1 加锁失败...");
            }
        }).start();

        new Thread(() -> {
            if (zkLock2.lock(RESOURCE)) {
                System.out.println("线程2 加锁成功...");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                zkLock2.unlock();
                System.out.println("线程2 释放锁...");
            } else {
                System.out.println("线程2 加锁失败...");
            }
        }).start();
    }

}
