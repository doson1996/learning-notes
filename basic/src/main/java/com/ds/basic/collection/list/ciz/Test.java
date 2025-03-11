package com.ds.basic.collection.list.ciz;

/**
 * @author ds
 * @date 2025/2/27
 * @description
 */
public class Test {
    public static void main(String[] args) {
        DsLinkedList<String> list = new DsLinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            System.out.println("str = " + s);
        }

    }
}
