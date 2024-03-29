package com.ds.basic.collection.list;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @Author ds
 * @Date 2021/5/11 9:50
 * @Description
 */
public class Demo02LinkedList {

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1, 2, 3};

        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);

        for (Integer integer : list) {
            System.out.println("integer = " + integer);
        }

        list.get(1);
        list.addAll(Arrays.asList(arr));
        System.out.println("list = " + list);
        // 指定位置连续几个数据
        System.out.println(list.subList(0, 2));
    }
}
