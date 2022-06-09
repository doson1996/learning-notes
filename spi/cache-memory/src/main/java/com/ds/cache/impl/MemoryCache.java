package com.ds.cache.impl;

import com.ds.cache.Cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ds
 */
public class MemoryCache implements Cache {

    private static final ConcurrentHashMap<String,Object> cache = new ConcurrentHashMap<>(64);

    @Override
    public Object get(String key) {
        return cache.get(key);
    }

    @Override
    public void set(String key, Object value) {
        cache.put(key, value);
    }
}
