package com.ds.basic.collection.map.hashmap;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ds
 * @date 2025/3/21
 * @description
 */
public class Demo02 {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        map.put("a", "a");
        System.out.println("map = " + map);
    }
}
