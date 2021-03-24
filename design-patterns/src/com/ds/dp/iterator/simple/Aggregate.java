package com.ds.dp.iterator.simple;

/**
 * @Author ds
 * @Date 2021/3/23 10:27
 * @Description 聚合对象的接口
 */
public abstract class Aggregate {

    /**
     * 工厂方法，创建相应迭代器的接口
     * @return 相应迭代器的接口
     */
    public abstract Iterator createIterator();
}
