package com.ds.dp.iterator.simple;

import java.util.Collections;

/**
 * @Author ds
 * @Date 2021/3/24 11:46
 * @Description 迭代器模式
 */
public class Client {

    public static void main(String[] args) {

        String[] ss = {"张三","李四","王五"};
        ConcreteAggregate concreteAggregate = new ConcreteAggregate(ss);
        Iterator it = new ConcreteIterator(concreteAggregate);
        it.first();
        while (!it.isDone()){
            Object o = it.currentItem();
            System.out.println("o= "+ o);
            it.next();
        }

    }
}
