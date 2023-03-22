package com.ds.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ds
 * @date 2023/3/22
 * @description 栈
 */
public class SimpleStack<E> {

    private final List<E> list = new ArrayList<>();

    public void push(E element) {

        list.add(element);
    }

    public E pop() {
        if (list.isEmpty()) {
            return null;
        }
        int index = list.size() - 1;
        E e = list.get(index);
        list.remove(index);
        return e;
    }

    public int size() {
        return list.size();
    }

}
