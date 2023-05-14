package com.ds.basic.collection.map.hashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ds
 * @date 2023/5/14
 * @description
 */
public class Demo02Print {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < 6; i++) {
            map.put(String.valueOf(i), i);
        }
        map.put("a", "a");
        map.put("A", "A");
        map.put("b", "b");
        map.put("d", "d");
        map.put("D", "D");
        PrintUtils.hashMap(map);
    }
}
