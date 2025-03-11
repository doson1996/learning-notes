package com.ds.basic.collection.map.hashmap;

import java.util.HashMap;

/**
 * @author ds
 * @date 2025/3/11
 * @description
 */
public class Demo03KeyChange {
    public static void main(String[] args) {
        Key key = new Key();
        key.setKey("key1");
        HashMap<Key, Object> map = new HashMap<>(16);
        map.put(key, 1);

        Object v = map.get(key);
        System.out.println("v = " + v);

        key.setKey("key2");
        Object v2 = map.get(key);
        System.out.println("v2 = " + v2);

        key.setKey("key1");
        Object v1 = map.get(key);
        System.out.println("v1 = " + v1);


    }
}
