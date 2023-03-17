package com.ds.basic.collection.list;

import java.util.*;

/**
 * @Author ds
 * @Date 2021/3/16 9:23
 * @Description
 */
public class Demo01ArrayList {

    public static void main(String[] args) {
        ArrayList<String> list1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list1.add("" + i);
        }
        ArrayList<String> list = new ArrayList<>(5);
        list.addAll(list1);
    }

    private static void m1() {
        List<Integer> list = new ArrayList<>();
        list.add(5, 1);

        String[] strings = {"1","2","3"};
        ArrayList<String> list1 = new ArrayList<>(Arrays.asList(strings));
        list1.add("1");
        System.out.println(list1);
        System.out.println(Arrays.toString(strings));
        List<String> list2 = Arrays.asList(strings);
        // list2.add("1");
        System.out.println(list2);

        List<String> synchronizedList = Collections.synchronizedList(new ArrayList<String>());
        synchronizedList.add("a");
        System.out.println("synchronizedList = " + synchronizedList);
    }

}
