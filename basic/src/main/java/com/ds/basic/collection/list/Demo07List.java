package com.ds.basic.collection.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ds
 * @date 2024/4/29
 * @description
 */
public class Demo07List {
    public static void main(String[] args) {
        List<Map<String,Object>> list = new ArrayList<>();
        Map<String,Object> data = new HashMap<>();
        data.put("b", null);
        list.add(data);

        List<Map<String,Object>> list1 = new ArrayList<>();
        list.forEach(jj -> {
            HashMap<String, Object> map = new HashMap<>();
            map.put("k", jj.get("b"));
           list1.add(map);
        }

        );

        System.out.println(list1);


    }
}
