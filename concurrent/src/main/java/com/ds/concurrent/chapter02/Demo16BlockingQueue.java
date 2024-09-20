package com.ds.concurrent.chapter02;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Author ds
 * @Date 2021/4/16 14:13
 * @Description 阻塞队列
 * ArrayBlockingQueue：     一个由数组结构组成的有界阻塞队列。
 * LinkedBlockingQueue：    一个由链表结构组成的有界阻塞队列。
 * PriorityBlockingQueue：  一个支持优先级排序的无界阻塞队列。
 * DelayQueue：             一个使用优先级队列实现的无界阻塞队列。
 * SynchronousQueue：       一个不存储元素的阻塞队列。
 * LinkedTransferQueue：    一个由链表结构组成的无界阻塞队列。
 * LinkedBlockingDeque：    一个由链表结构组成的双向阻塞队列。
 */
public class Demo16BlockingQueue {

    public static void main(String[] args) {

        ArrayBlockingQueue fairQueue = new ArrayBlockingQueue(1);


    }
}
