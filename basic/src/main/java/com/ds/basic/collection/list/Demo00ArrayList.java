package com.ds.basic.collection.list;

import java.util.ArrayList;

/**
 * @author ds
 * @date 2023/5/11
 * @description
 */
public class Demo00ArrayList {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i == 5) {
                list.add(0, i);
            } else {
                list.add(i);
            }
        }

        System.out.println("list = " + list);
    }
}
