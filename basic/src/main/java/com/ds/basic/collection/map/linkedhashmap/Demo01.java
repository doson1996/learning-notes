package com.ds.basic.collection.map.linkedhashmap;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * @author ds
 */
public class Demo01 {
    public static void main(String[] args) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        for (int i = 10; i > 0; i--) {
            map.put(i + "", i);
        }
        System.out.println(map);

        HashMap<String, Object> hashMap = new HashMap<>();
        for (int i = 10; i > 0; i--) {
            hashMap.put(i + "", i);
        }
        System.out.println(hashMap);
    }
}
