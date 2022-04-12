package com.ds.mq.rocket.order;

import com.ds.mq.rocket.config.Config;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Simple Message Example
 * 发送有序消息
 * @author ds
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("Group_order_producer");
        producer.setNamesrvAddr(Config.NAME_SERVER_ADDRESSES);
        producer.start();

        for (int i = 0; i < 10; i++) {
            Message message = new Message();
            message.setTopic("Topic_Order");
            message.setTags("Tag_Order");
            message.setKeys("Key_Order");
            message.setBody(("hello rocketmq" + i ).getBytes(StandardCharsets.UTF_8));
            SendResult result = producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> messageQueues, Message message, Object arg) {
                    return messageQueues.get((Integer) arg);
                }
            }, 0);
            System.out.println("result = " + result);
        }


        producer.shutdown();
    }
}
