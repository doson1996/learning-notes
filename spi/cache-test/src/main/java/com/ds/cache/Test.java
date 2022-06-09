package com.ds.cache;

import java.util.ServiceLoader;

/**
 * @author ds
 */
public class Test {
    public static void main(String[] args) {
        ServiceLoader<Cache> caches = ServiceLoader.load(Cache.class);
        for (Cache c : caches) {
            System.out.println(c);
        }
    }
}
