package com.ds.cache;

import java.util.ServiceLoader;

import com.ds.cache.impl.DiskCache;

/**
 * @author ds
 */
public class Test {
    public static void main(String[] args) {
        ServiceLoader<Cache> caches = ServiceLoader.load(Cache.class);
        for (Cache c : caches) {
            System.out.println(c);
            if (c.getClass() == DiskCache.class) {
                c.set("key1", "value1");
                Object v1 = c.get("key1");
                System.out.println("v1 = " + v1);
            }
        }
    }
}
