package com.ds.redis.base;

import com.ds.redis.RedisClient;
import org.redisson.api.RBucket;
import org.redisson.api.RList;
import org.redisson.api.RedissonClient;

/**
 * @author ds
 * @date 2025/1/22
 * @description
 */
public class Demo01Basic {

    public static void main(String[] args) {
        RedissonClient redissonClient = RedisClient.getRedissonClient();
        RBucket<String> bucket = redissonClient.getBucket("test:key");
        bucket.set("1");

        RList<Object> list = redissonClient.getList("test:list");
        list.add(1);
        list.addBefore("1", "1");
        System.out.println("list = " + list);
        redissonClient.shutdown();
    }

}
