package com.ds.cache;

/**
 * 缓存
 * @author ds
 */
public interface Cache {

    /**
     * 获取数据
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 缓存数据
     * @param key
     * @param value
     * @return
     */
    void set(String key, Object value);

}
