package com.ds.cache.impl;

import com.ds.cache.Cache;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ds
 */
public class DiskCache implements Cache {

    private static final ConcurrentHashMap<String,Object> cache = new ConcurrentHashMap<>(64);

    @Override
    public Object get(String key) {
        return cache.get(key);
    }

    @Override
    public void set(String key, Object value) {
        Path path = Paths.get("cache.txt");
        cache.put(key, value);
    }
}
