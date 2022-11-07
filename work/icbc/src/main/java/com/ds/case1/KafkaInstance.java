package com.ds.case1;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ds
 */
public class KafkaInstance {

    private static final Map<String, Producer> producerMap = new HashMap<>();

    private static Producer producer = null;

    private KafkaInstance() {
    }

    private static KafkaInstance kafkaInstance = null;

    public static synchronized KafkaInstance newInstance() {
        if (kafkaInstance == null) {
            kafkaInstance = new KafkaInstance();
        }
        return kafkaInstance;
    }

    public Producer getProducer(String topicPartition) {
        if ((producer = producerMap.get(topicPartition)) != null) {
            return producer;
        }
        return initProducer(topicPartition);
    }

    private Producer initProducer(String topicPartition) {
        //模拟日志打印
        System.out.println("[KafkaInstance.initProducer] topicPartition：" + topicPartition + ", producerMap size: " + producerMap.size());
        producer = new KafkaProducer();
        producerMap.put(topicPartition, producer);
        return producer;
    }

}
