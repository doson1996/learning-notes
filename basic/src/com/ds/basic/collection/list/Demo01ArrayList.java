package com.ds.basic.collection.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author ds
 * @Date 2021/3/16 9:23
 * @Description
 */
public class Demo01ArrayList {

    public static void main(String[] args) {

        String[] strings = {"1","2","3"};
        ArrayList<String> list1 = new ArrayList<>(Arrays.asList(strings));
        list1.add("1");
        System.out.println(list1);
        List<String> list2 = Arrays.asList(strings);
        list2.add("1");
        System.out.println(list2);

    }
}
