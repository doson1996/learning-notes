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
     * 元素个数
     */
    private int size;

    public SingleLinkedList() {
        size = 0;
    }

    /**
     * 新增元素
     *
     * @param element element whose presence in this collection is to be ensured
     * @return
     */
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
        while (node.next != null) {
            node = node.next;
        }
        node.next = newNode;
        size++;
        return true;
    }

    /**
     * 获取第index位元素
     *
     * @param index index of the element to return
     * @return
     */
    @Override
    public E get(int index) {
        checkIndex(index);
        if (index == 0) {
            return first.element;
        }

        Node<E> node = first.next;
        for (int i = 1; i < size; i++) {
            if (index == i)
                return node.element;
            // 继续往下寻找
            else
                node = node.next;
        }
        // 下标大于size，返回null
        return null;
    }

    /**
     * 删除第index位元素
     *
     * @param index the index of the element to be removed
     * @return
     */
    @Override
    public E remove(int index) {
        checkIndex(index);
        // 返回的元素
        E element;
        // 第一个节点
        if (index == 0) {
            element = first.element;
            first = first.next;
            size--;
            return element;
        }
        // 当前节点
        Node<E> current = first.next;
        // 记录上一个节点
        Node<E> prev = first;
        for (int i = 1; i < size; i++) {
            if (index == i) {
                element = current.element;
                // 找到之后，下一个节点变成当前节点
                current = current.next;
                // 然后下一个节点去连接上一个节点
                prev.next = current;
                size--;
                return element;
            // 继续往下寻找
            } else {
                // 当前节点赋值给上一个节点
                prev = current;
                // 下一个节点赋值给当前节点
                current = current.next;
            }
        }
        // 链表中没有此下标
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
        private final E element;

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
