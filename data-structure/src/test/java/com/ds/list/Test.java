package com.ds.list;

import java.util.List;

/**
 * @author ds
 * @date 2023/3/17
 * @description
 */
public class Test {

    public static void main(String[] args) {
        List<String> list = new SingleLinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }

        System.out.println(list);

        System.out.println(list.remove(0));
        System.out.println(list.remove(3));

        System.out.println(list);
    }

}
