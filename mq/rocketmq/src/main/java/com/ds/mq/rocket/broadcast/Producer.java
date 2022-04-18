package com.ds.mq.rocket.broadcast;

import com.ds.mq.rocket.config.Config;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ds
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("group_broadcast_producer");
        producer.setNamesrvAddr(Config.NAME_SERVER_ADDRESSES);
        producer.start();

        List<Message> messages = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Message message = new Message();
            message.setTopic("Topic_Broadcast");
            message.setTags("Tag_Broadcast");
            message.setKeys("Key_Broadcast");
            message.setBody(("hello rocketmq" + i).getBytes(StandardCharsets.UTF_8));
            messages.add(message);
        }

        SendResult result = producer.send(messages);
        System.out.println("result = " + result);

        producer.shutdown();
    }
}
