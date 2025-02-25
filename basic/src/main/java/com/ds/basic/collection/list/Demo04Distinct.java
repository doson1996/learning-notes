package com.ds.basic.collection.list;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 去重
 *
 * @author ds
 */
public class Demo04Distinct {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
            list.add(i + "");
        }
        list.stream().distinct().collect(Collectors.toList());
        System.out.println("list = " + list);
        list = list.stream().distinct().collect(Collectors.toList());
        System.out.println("list = " + list);

        Set<String> set = new HashSet<>(list);
        set.add("10");
        System.out.println("set = " + set);

    }
}
