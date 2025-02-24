package com.ds.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author ds
 * @date 2025/1/22
 * @description
 */
public class RedisClient {

    public static final RedissonClient getRedissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://ds.com:6379");
        config.useSingleServer().setPassword("123456");
        return Redisson.create(config);
    }

}
