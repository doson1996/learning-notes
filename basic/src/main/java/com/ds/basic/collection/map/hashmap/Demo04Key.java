package com.ds.basic.collection.map.hashmap;

import java.util.HashMap;

/**
 * @author ds
 * @date 2025/3/11
 * @description
 */
public class Demo04Key {
    static Key key;

    public static void main(String[] args) {
        HashMap<Key, Object> map = new HashMap<>();

        Key key1 = new Key();
        key1.setKey("key");
        key = key1;
        System.out.println("key1 = " + key1);
        map.put(key1, 1);

        Key key2 = new Key();
        key2.setKey("key");
        map.put(key2, 2);

        Object v1 = map.get(key1);
        System.out.println("v1 = " + v1);
        Object v2 = map.get(key2);
        System.out.println("v2 = " + v2);
    }
}
