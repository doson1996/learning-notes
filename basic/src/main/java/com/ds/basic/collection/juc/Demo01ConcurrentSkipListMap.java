package com.ds.basic.collection.juc;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * @Author ds
 * @Date 2021/3/31 14:53
 * @Description
 */
public class Demo01ConcurrentSkipListMap {

    public static void main(String[] args) {

        ConcurrentSkipListMap<String, Object> map = new ConcurrentSkipListMap<>();
        map.put("1", 1);
        map.put("2", 1);
        map.put("3", 1);
        map.put("4", 1);
        System.out.println(map);

        Object o = map.get("3");

    }
}
