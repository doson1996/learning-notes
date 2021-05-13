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
        list.get(1);
        list.addAll(Arrays.asList(arr));
        System.out.println("list = " + list);
    }
}
