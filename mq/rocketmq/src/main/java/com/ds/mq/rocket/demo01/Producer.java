package com.ds.mq.rocket.demo01;

import com.ds.mq.rocket.config.Config;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;

/**
 * Simple Message Example
 * 同步发送消息
 * @author ds
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("Group_Demo_producer");
        producer.setNamesrvAddr(Config.NAME_SERVER_ADDRESSES);
        producer.start();

        Message message = new Message();
        message.setTopic("Topic_Demo");
        message.setTags("Tag_Demo");
        message.setKeys("Key_Demo");
        message.setBody("hello rocketmq".getBytes(StandardCharsets.UTF_8));
        SendResult result = producer.send(message);
        System.out.println("result = " + result);

        producer.shutdown();
    }
}
