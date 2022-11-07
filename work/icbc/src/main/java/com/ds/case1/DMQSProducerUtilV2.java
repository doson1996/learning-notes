package com.ds.case1;

import java.util.HashMap;
import java.util.Map;

/**
 * dmqs v2
 * 使用hashmap做池化，并发下第一次携带相同topicPartition进来的线程会还是会重复创建Producer对象和线程，
 * 但相较于第一版，不会导致内存cpu冲高容器不断重启。
 * <p>
 * initProducer应该只做一件事，producerMap.put不该在此方法
 *
 * @author ds
 */
public class DMQSProducerUtilV2 {

    private static Producer producer = null;

    private static final Map<String, Producer> producerMap = new HashMap<>();

    private DMQSProducerUtilV2() {
    }

    public static Producer getProducer(String topicPartition) {
        if ((producer = producerMap.get(topicPartition)) != null) {
            return producer;
        }
        return initProducer(topicPartition);
    }

    private static Producer initProducer(String topicPartition) {
        //模拟日志打印
        System.out.println("[DMQSProducerUtilV2.initProducer] topicPartition：" + topicPartition + ", producerMap size: " + producerMap.size());
        producer = new DMQSProducer();
        producerMap.put(topicPartition, producer);
        return producer;
    }

}
