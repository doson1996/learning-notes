package com.ds.case1;

/**
 * 模拟消息发送工具类
 *
 * @author ds
 */
public class MsgSendUtil {

    /**
     * 发送
     *
     * @param msg
     * @param topic
     * @return
     */
    public static boolean send(String msg, String topic) {
        // 模拟构建成TopicPartition
        String topicPartition = topic;
        Producer producer;
        // 模拟从数据库开关表查询
        String dmqsSwitch = QueryParamUtil.querySwitch("1");
        if ("1".equals(dmqsSwitch)) {
            // DMQS方式
            //第一版
            //producer = DMQSProducerUtilV1.getProducer(topicPartition);
            //第二版
            //producer = DMQSProducerUtilV2.getProducer(topicPartition);
            //第三版
            producer = DMQSProducerUtilV3.getDmqsProducerUtilV3().getProducer(topicPartition);

        } else {
            // kafka方式
            KafkaInstance kafkaInstance = KafkaInstance.newInstance();
            producer = kafkaInstance.getProducer(topicPartition);
        }

        return producer.send(msg);
    }


}
