package com.ds.basic.collection.set;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * @Author ds
 * @Date 2021/5/11 11:09
 * @Description
 */
public class Demo01 {

    public static void main(String[] args) {

        Set<Integer> set = new TreeSet<>((o1, o2) -> o2 - o1);
        set.add(1);
        set.add(1);
        set.add(5);
        set.add(2);
        System.out.println("set = " + set);

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(1);
        list.add(2);
        List<Integer> collect = list.stream().distinct().filter(a-> a > 1).collect(Collectors.toList());
        System.out.println("collect = " + collect.toString());

    }
}
