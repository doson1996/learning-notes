package com.ds.source.code.hashmap;

/**
 * @author ds
 * @date 2025/3/31
 * @description
 */
public class HashMapCode {
    public static void main(String[] args) {

    }
/*
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
        //定义了一个用于表示哈希表的数组 tab，一个节点 p 用于指向特定的哈希桶，
        // 以及两个整型变量 n 和 i 用于存储哈希表的大小和计算的索引位置。
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        // 先判断当前hashmap有没有初始化
        if ((tab = table) == null || (n = tab.length) == 0)
            // 进行初始化（懒加载）
            n = (tab = resize()).length;
        // 计算键的哈希值应该映射到的索引，并检查该位置是否为空。如果为空，构建一个node对象置于该位置  【并发情况，这里可能会发生线程安全问题，出现put的value丢失的情况】
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
            // 如果该位置不为空（hash冲突）
        else {
            Node<K,V> e; K k;
            // 判断链表第一个元素的key和put的key是不是一样，一样的话将e赋值为该链表第一个元素（后面e.value = value; 更新value）
            if (p.hash == hash &&
                    ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
                // 链表第一个元素的key和put的key不一样，则进行遍历
            else {
                for (int binCount = 0; ; ++binCount) {
                    // 如果链表当前元素的下一个节点为空，则构建一个node对象置为下一个节点（hash冲突，但是是新增key的情况）
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    // 如果链表当前元素的key和put的key相等，跳出循环 （key已存在于当前map）
                    if (e.hash == hash &&
                            ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    p = e;
                }
            }
            if (e != null) { // existing mapping for key
                V oldValue = e.value;
                if (!onlyIfAbsent || oldValue == null)
                    // 更新找到对应元素的value
                    e.value = value;
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        if (++size > threshold)
            resize();
        afterNodeInsertion(evict);
        return null;
    }
    */
}


