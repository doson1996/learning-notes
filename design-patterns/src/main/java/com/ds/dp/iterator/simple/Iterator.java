package com.ds.dp.iterator.simple;

/**
 * @Author ds
 * @Date 2021/3/23 9:25
 * @Description 迭代器接口
 */
public interface Iterator {

    /**
     *  移动聚合对象到第一个位置
     */
    void first();

    /**
     * 移动聚合对象到下一个位置
     */
    void next();

    /**
     *  判断聚合对象是否移动到最后一个位置
     * @return true  已经移到最后一个位置
     *         false 没有移到最后一个位置
     */
    boolean isDone();

    /**
     * 返回当前迭代的元素
     * @return
     */
    Object currentItem();

}
