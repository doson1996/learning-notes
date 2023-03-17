package com.ds.list;

import java.util.List;

/**
 * @author ds
 * @date 2023/3/17
 * @description
 */
public class Test {

    public static void main(String[] args) {
        List<String> list = new DsLinkedList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        for (String s : list) {
            System.out.println("s = " + s);
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        System.out.println(list);
    }

}
