package com.ds.list;

import java.util.AbstractList;
import java.util.List;

/**
 * @author ds
 * @date 2023/3/17
 * @description
 */
public class DsLinkedList<E> extends AbstractList<E> implements List<E> {

    private Node<E> first;

    private Node<E> last;

    private int size;

    public DsLinkedList() {
        size = 0;
    }

    @Override
    public boolean add(E e) {
        size++;
        if (first == null) {
            first = new Node<>(null, e, null);
            last = first;
            return true;
        }
        last = new Node<>(last, e, null);
        last.pre.next = last;
        return true;
    }

    @Override
    public E get(int index) {
        Node<E> node = first;
        if (index == 0)
            return first.value;
        for (int i = 1; i < size; i++) {
            node = node.next;
            if (i == index)
                return node.value;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    private static class Node<E> {

        private final E value;

        private Node<E> pre;

        private Node<E> next;

        public Node(E value) {
            this.value = value;
        }

        public Node(Node<E> pre, E value, Node<E> next) {
            this.value = value;
            this.pre = pre;
            this.next = next;
        }

        public Node<E> getPre() {
            return pre;
        }

        public void setPre(Node<E> pre) {
            this.pre = pre;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }

}
