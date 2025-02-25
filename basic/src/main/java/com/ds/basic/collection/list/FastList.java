package com.ds.basic.collection.list;

import java.util.AbstractList;
import java.util.List;

/**
 * @author ds
 * @date 2023/5/11
 * @description
 */
public class FastList<E> extends AbstractList<E> implements List<E> {

    public FastList() {
        elementData = new Object[10];
    }

    public FastList(int capacity) {
        elementData = new Object[capacity];
    }

    /**
     *
     */
    transient Object[] elementData;

    private int size;

    @Override
    public boolean add(E e) {
        elementData[size] = e;
        size++;
        return true;
    }

    @Override
    public E get(int index) {
        return (E) elementData[index];
    }

    @Override
    public E remove(int index) {
        E oldValue = (E) elementData[index];
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(elementData, index + 1, elementData, index,
                    numMoved);
        elementData[--size] = null;
        return oldValue;
    }

    @Override
    public int size() {
        return this.size;
    }

}
