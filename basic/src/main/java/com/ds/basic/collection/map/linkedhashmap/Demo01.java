package com.ds.basic.collection.map.linkedhashmap;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ds
 */
public class Demo01 {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 10; i > 0; i--) {
            map.put(i + "", i);
        }
        System.out.println(map);
    }
}
