package com.ds.redis.publish;

import cn.hutool.json.JSONObject;
import org.redisson.Redisson;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

/**
 * @author ds
 * @date 2025/1/21
 * @description
 */
public class Provider {
    public static void main(String[] args) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://ds.com:6379");
        config.useSingleServer().setPassword("123456");
        RedissonClient redisson = Redisson.create(config);

        RTopic topic = redisson.getTopic("test-topic");
        JSONObject json = new JSONObject();
        json.set("msg", "hello");
        json.set("code", 200);
        topic.publish(json.toStringPretty());

        redisson.shutdown();
    }
}
