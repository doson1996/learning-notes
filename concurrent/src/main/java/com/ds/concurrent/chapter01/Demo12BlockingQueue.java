package com.ds.concurrent.chapter01;

import java.util.concurrent.*;

/**
 * @Author ds
 * @Date 2021/3/29 11:21
 * @Description 阻塞队列
 *              ArrayBlockingQueue
 *                  可以初始化队列⼤⼩， 且⼀旦初始化不能改变。构造⽅法中的fair表示控制对象的内部锁是否采⽤公平锁，默认是⾮公平锁。
 *              LinkedBlockingQueue
 *                  由链表结构组成的有界阻塞队列。内部结构是链表，具有链表的特性。默认队列的⼤⼩是 Integer.MAX_VALUE ，也可以指定⼤⼩。
 *                  此队列按照先进先出的原则对元素进⾏排序。
 *              DelayQueue
 *                  该队列中的元素只有当其指定的延迟时间到了，才能够从队列中获取到该元素 。注⼊其中的元素必须实现DelayQueue是⼀个没有⼤⼩限制的队列，
 *                  因此往队列中插⼊数据的操作（⽣产者）永远不会被阻塞
 *              PriorityBlockingQueue
 *
 *              SynchronousQueue
 *                  这个队列⽐较特殊，没有任何内部容量，甚⾄连⼀个队列的容量都没有。并且每个put 必须等待⼀个 take，反之亦然。
 *
 *
 */
public class Demo12BlockingQueue {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> abq = new ArrayBlockingQueue<>(1,true);
        abq.put("a");

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                String take = abq.take();
                System.out.println("take = " + take);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        thread.start();

        abq.put("b");
        System.out.println("abq = " + abq);
    }

}
