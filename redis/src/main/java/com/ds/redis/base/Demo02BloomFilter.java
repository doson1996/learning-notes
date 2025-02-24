package com.ds.redis.base;

import com.ds.redis.RedisClient;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;

/**
 * @author ds
 * @date 2025/1/22
 * @description
 */
public class Demo02BloomFilter {
    public static void main(String[] args) {
        RedissonClient redissonClient = RedisClient.getRedissonClient();
        RBloomFilter<String> bf = redissonClient.getBloomFilter("bf");
        bf.tryInit(10000, 0.01);

        bf.add("1");
        bf.add("2");

        boolean contains = bf.contains("1");
        System.out.println("contains = " + contains);
        boolean contains1 = bf.contains("3");
        System.out.println("contains1 = " + contains1);

        redissonClient.shutdown();
    }
}
