package com.ds.case1;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * dmqs v3
 * 优化后第三版，解决了第二版并发下创建多余对象和线程的问题
 *
 * @author ds
 */
public class DMQSProducerUtilV3 {

    private static final Map<String, Producer> producerMap = new ConcurrentHashMap<>();

    private volatile static DMQSProducerUtilV3 dmqsProducerUtilV3;

    public static DMQSProducerUtilV3 getDmqsProducerUtilV3() {
        if (dmqsProducerUtilV3 == null) {
            synchronized (DMQSProducerUtilV3.class) {
                if (dmqsProducerUtilV3 == null) {
                    dmqsProducerUtilV3 = new DMQSProducerUtilV3();
                }
            }
        }
        return dmqsProducerUtilV3;
    }

    private DMQSProducerUtilV3() {
    }

    public Producer getProducer(String topicPartition) {
        return producerMap.computeIfAbsent(topicPartition, k -> initProducer(topicPartition));
    }

    private Producer initProducer(String topicPartition) {
        //模拟日志打印
        System.out.println("[DMQSProducerUtilV3.initProducer] topicPartition：" + topicPartition + ", producerMap size: " + producerMap.size());
        return new DMQSProducer();
    }

}
