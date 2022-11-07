package com.ds.case1;

/**
 * kafka升级dmqs生产者遇到的问题和优化
 *
 * 消费者遇到的问题：
 *  LocalTransaction -> ThreadLocal引发
 * @author ds
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        // 模拟不同系统访问
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                IPVSServiceImpl.exec(10000);
            }).start();
        }

        Thread.sleep(20000);
    }
}
