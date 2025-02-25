package com.ds.dp.flyweight.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author ds
 * @Date 2021/4/6 10:03
 * @Description
 */
public class FlyweightFactory {

    Map<String, Flyweight> fs = new HashMap<>();

    public Flyweight getFlyweight(String key) {
        Flyweight flyweight = fs.get(key);

        if (flyweight == null) {

            flyweight = new ConcreteFlyweight(key);
            fs.put(key, flyweight);
        }

        return flyweight;
    }
}
