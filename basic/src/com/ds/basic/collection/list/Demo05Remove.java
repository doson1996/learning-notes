package com.ds.basic.collection.list;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ds
 */
public class Demo05Remove {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
            list.add(i + "");
        }

        for (int i = 0; i < list.size(); i++) {
            if ("1".equals(list.get(i))) {
              //  list.remove(i);
            }
        }

        list = list.stream().filter(s-> !"2".equals(s)).collect(Collectors.toList());
        System.out.println("list = " + list);
        list.removeIf("1"::equals);
        System.out.println("list = " + list);
        list = list.parallelStream().filter(s-> !"5".equals(s)).collect(Collectors.toList());
        System.out.println("list = " + list);
    }
}
