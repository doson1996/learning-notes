package com.ds.basic.collection.juc;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ds
 * @date 2025/7/28
 * @description
 */
public class Demo03ConcurrentHashMap {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        map.put("1", 1);
        map.put("1", 1);

        Object v = map.get("1");
        System.out.println("v = " + v);
    }
}
