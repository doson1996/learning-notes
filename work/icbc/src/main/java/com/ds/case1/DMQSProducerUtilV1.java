package com.ds.case1;

/**
 * dmqs v1
 * 没用map做池化，创建大量DMQSProducer对象和线程，导致内存cpu冲高容器不断重启。
 * @author ds
 */
public class DMQSProducerUtilV1 {

    private DMQSProducerUtilV1() {
    }

    public static Producer getProducer(String topicPartition) {
        return initProducer(topicPartition);
    }

    private static Producer initProducer(String topicPartition) {
        //模拟日志打印
        System.out.println("[DMQSProducerUtilV1.initProducer] topicPartition：" + topicPartition);
        return new DMQSProducer();
    }

}
