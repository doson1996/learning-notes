package com.ds.dp.iterator.example;

/**
 * @author ds
 * @date 2023/5/11
 * @description
 */
public interface Iterator<E> {

    /**
     * 是否有下一个元素
     * @return
     */
    boolean hasNext();

    /**
     * 获取下一个元素
     * @return
     */
    E next();

    /**
     * 移除元素
     */
    void remove();

}
