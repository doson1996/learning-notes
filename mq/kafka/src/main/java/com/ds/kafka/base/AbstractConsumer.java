package com.ds.kafka.base;

import com.ds.kafka.config.Config;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

/**
 * @author ds
 */
public abstract class AbstractConsumer implements Config {

    public KafkaConsumer<String, Object> initKafkaConsumer() {
        Properties properties = defineDefaultConfiguration();
        return new KafkaConsumer<>(properties);
    }

    private Properties defineDefaultConfiguration() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, CLIENT_ID);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        put(properties);
        remove(properties);
        return properties;
    }

    /**
     * 新增配置
     *
     * @param properties
     */
    public void put(Properties properties) {

    }

    /**
     * 减少配置
     *
     * @param properties
     */
    public void remove(Properties properties) {

    }

}
