package com.ds.basic.collection.set;

import java.util.HashSet;
import java.util.Set;

/**
 * @author ds
 * @date 2021/8/27 23:17
 */
public class Demo02 {
    public static void main(String[] args) {
        Set<String> set1 = new HashSet<>();
        set1.add("a");
        set1.add("b");
        set1.add("c");
        set1.add("d");
        set1.add("e");

        Set<String> set2 = new HashSet<>();
        set2.add("a");
        set2.add("b");
        set2.add("x");
        set2.add("y");

        //交集
        set1.retainAll(set2);
        System.out.println(set1);

        //并集
        set1.addAll(set2);
        System.out.println(set1);

        //差集
        set1.removeAll(set2);
        System.out.println(set1);
    }
}
