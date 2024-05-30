package com.ds.basic.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ds
 * @date 2024/5/18 16:27
 */
public class Demo01 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }

        list = list.stream().filter(num -> num > 5).collect(Collectors.toList());
        System.out.println(list);
    }
}
