package com.ds.list;

import java.util.LinkedList;
import java.util.List;

/**
 * @author ds
 * @date 2023/3/17
 * @description
 */
public class Test {

    public static void main(String[] args) {
        //m1();
        long start = System.currentTimeMillis();
        m4();
        System.out.println("耗时: " + (System.currentTimeMillis() - start) + "ms");
    }

    public static void m1() {
        List<String> list = new SingleLinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add("" + i);
        }

        System.out.println(list);

        System.out.println(list.remove(0));
        System.out.println(list.remove(3));

        System.out.println(list);
    }

    public static void m2() {
        List<String> list = new LinkedList<>();
        for (int i = 0; i < 100000; i++) {
            list.add(i + "");
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static void m3() {
        List<String> list = new SingleLinkedList<>();
        for (int i = 0; i < 100000; i++) {
            list.add(i + "");
        }
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
        System.out.println("list.size() = " + list.size());
    }

    public static void m4() {
        List<String> list = new SingleLinkedListV1<>();
        for (int i = 0; i < 100000; i++) {
            list.add(i + "");
        }
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i));
//        }
        System.out.println("list.size() = " + list.size());
    }
}
