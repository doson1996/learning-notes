package com.ds.list;

import java.util.AbstractList;
import java.util.List;

/**
 * @author ds
 * @date 2023/3/17
 * @description 单向链表
 */
public class SingleLinkedList<E> extends AbstractList<E> implements List<E> {

    /**
     * 头节点
     */
    private Node<E> first;

    /**
     * 尾节点
     */
    private Node<E> last;

    /**
     * 元素个数
     */
    private int size;

    public SingleLinkedList() {
        size = 0;
    }

    @Override
    public boolean add(E element) {
        Node<E> newNode = new Node<>(element, null);
        // 第一次add
        if (first == null) {
            first = newNode;
            size++;
            return true;
        }
        // 不是第一次add
        Node<E> node = first;
//        while (node.next != null) {
//            node = node.next;
//        }

        for (int i = 1; i < size; i++) {
            node = node.next;
        }
        node.next = newNode;
        size++;
        return true;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        if (index == 0) {
            return first.element;
        }

        Node<E> node = first.next;

        int i = 1;
        // 如果查找的下标大于size的一半，从二分之一开始查找
        if (index > size / 2)
            i = size / 2;
        for (; i < size; i++) {
            if (index == i)
                return node.element;
            else
                node = node.next;
        }
        // 下标大于size，返回null
        return null;
    }

    /**
     * 校验下标
     *
     * @param index
     */
    private void checkIndex(int index) {
        if (index < 0)
            throw new IndexOutOfBoundsException("下标[" + index + "]越界! ");
    }

    @Override
    public int size() {
        return size;
    }

    private static class Node<E> {

        /**
         * 元素
         */
        private E element;

        /**
         * 下一个节点
         */
        private Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

    }

}
