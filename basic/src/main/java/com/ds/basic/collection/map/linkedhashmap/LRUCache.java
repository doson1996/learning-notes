package com.ds.basic.collection.map.linkedhashmap;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author ds
 * @date 2026/7/10
 * @description
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private final Integer maxCapacity;

    public LRUCache(int maxCapacity) {
        // 注意：initialCapacity 根据容量计算，loadFactor 默认 0.75，accessOrder = true
        super((int) Math.ceil(maxCapacity / 0.75f) + 1, 0.75f, true);
        this.maxCapacity = maxCapacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        // 当元素数量超过最大容量时，返回 true 表示移除最老元素
        return size() > maxCapacity;
    }

    public static void main(String[] args) {
        LRUCache<String, Integer> cache = new LRUCache<>(3);
        cache.put("A", 1);
        cache.put("B", 2);
        cache.put("C", 3);
        System.out.println(cache.keySet()); // [A, B, C] (插入顺序)

        cache.get("A");                     // 访问 A，A 移到尾部
        cache.put("D", 4);                  // 插入 D，容量超限，移除头部（最久未访问的 B）
        System.out.println(cache.keySet()); // [C, A, D] (B 被移除)
    }

}
