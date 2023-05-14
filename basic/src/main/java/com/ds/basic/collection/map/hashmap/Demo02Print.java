package com.ds.basic.collection.map.hashmap;

import java.util.*;

/**
 * @author ds
 * @date 2023/5/14
 * @description
 */
public class Demo02Print {
    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();
        for (int i = 0; i < 6; i++) {
          //  map.put(String.valueOf(i), i);
        }
        map.put("1", "1");
        map.put("A", "A");
        map.put("a", "a");
        map.put("Q", "Q");
        map.put("q", "q");
        map.put("Á", "Á");
        map.put("á", "á");
        PrintUtils.hashMap(map);
        map.put("Ʊ", "Ʊ");
        map.put("ǁ", "ǁ");

       // map.put("b", "b");
//        map.put("d", "d");
//        map.put("D", "D");
        PrintUtils.hashMap(map);
    }
}
