package com.ds.concurrent.chapter01;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Author ds
 * @Date 2021/3/29 15:06
 * @Description     CopyOnWriteArrayList
 *                      优点：CopyOnWriteArrayList经常被⽤于“读多写少”的并发场景，是因为CopyOnWriteArrayList⽆需任何同步措施，
 *                          ⼤⼤增强了读的性能。在Java中遍历线程⾮安全的List(如：ArrayList和 LinkedList)的时候，若中途有别的线程对List容器
 *                          进⾏修改，那么会抛出ConcurrentModificationException异常。 CopyOnWriteArrayList由于其"读写分离"，
 *                          遍历和修改操作分别作⽤在不同的List容器，所以在使⽤迭代器遍历的时候，则不会抛出异常
 *
 *                      缺点：1.CopyOnWriteArrayList每次执⾏写操作都会将原容器进⾏拷⻉了⼀份，数据量⼤的时候，内存会存在较⼤的压⼒，
 *                          可能会引起频繁FullGC。⽐如这些对象占⽤的内存⽐较⼤200M左右，那么再写⼊100M数据进去，内存就会多占⽤300M。
 *                          2.CopyOnWriteArrayList由于实现的原因，写和读分别作⽤在不同新⽼容器上，在写操作执⾏过程中，读不会阻塞，但读取到的却是⽼容器的数据。
 */
public class Demo14CopyOnWriteArrayList {

    public static void main(String[] args) {

        List<Integer> list = new CopyOnWriteArrayList<>();
        list.add(1);
        Integer res = list.get(0);
        list.remove(0);
        System.out.println(res);

    }
}
