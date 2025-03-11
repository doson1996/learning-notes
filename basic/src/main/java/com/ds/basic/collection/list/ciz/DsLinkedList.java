package com.ds.basic.collection.list.ciz;

/**
 * @author ds
 * @date 2025/2/27
 * @description
 */
public class DsLinkedList<E> {

    private int size;

    /**
     * 头结点
     */
    private Node<E> head;

    /**
     * 尾结点
     */
    private Node<E> tail;

    public E get(int index) {
        if (head == null) {
            return null;
        }

        Node<E> node = head;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return node.data;
            }
            node = node.next;
        }

        return null;
    }

    public boolean add(E e) {
        size++;

        // 如果头结点为空
        if (head == null) {
            Node<E> newNode = new Node<>(null, e, null);
            head = newNode;
            tail = newNode;
            return true;
        }

        // 添加到尾结点
        Node<E> t = tail;
        Node<E> newNode = new Node<>(t, e, null);
        t.next = newNode;
        tail = newNode;
        return true;
    }

    public int size() {
        return size;
    }

    protected class Node<E> {

        E data;

        Node<E> pre;

        Node<E> next;

        public Node(Node<E> pre, E data, Node<E> next) {
            this.pre = pre;
            this.data = data;
            this.next = next;
        }

    }

}