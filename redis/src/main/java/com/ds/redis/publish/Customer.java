package com.ds.redis.publish;

import org.redisson.Redisson;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author ds
 * @date 2025/1/21
 * @description
 */
public class Customer {
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://ds.com:6379");
        config.useSingleServer().setPassword("123456");
        RedissonClient redisson = Redisson.create(config);
        RTopic topic = redisson.getTopic("test-topic");

        topic.addListener(String.class, (channel, msg) -> {
            System.out.println("channel = " + channel);
            System.out.println("msg = " + msg);
        });

    }
}
