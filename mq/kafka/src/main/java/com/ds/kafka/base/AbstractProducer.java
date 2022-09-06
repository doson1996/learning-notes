package com.ds.kafka.base;

import com.ds.kafka.config.Config;
import com.ds.kafka.producer.interceptor.Producer1;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author ds
 */
public abstract class AbstractProducer implements Config {

    public KafkaProducer<String, Object> initKafkaProducer() {
        Properties properties = defineDefaultConfiguration();
        return new KafkaProducer<>(properties);
    }

    private Properties defineDefaultConfiguration() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, CLIENT_ID);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
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

    /**
     * 设置具体producer实例
     *
     * @return
     */
    public abstract Class<? extends AbstractProducer> setClass();


}
