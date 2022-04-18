package com.ds.mq.rocket.broadcast;

import com.ds.mq.rocket.config.Config;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author ds
 */
public class ConsumerA {
    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group_broadcast_consumer");
        consumer.setNamesrvAddr(Config.NAME_SERVER_ADDRESSES);
        consumer.setMessageModel(MessageModel.BROADCASTING);
        consumer.subscribe("Topic_Broadcast", "*");

        consumer.registerMessageListener(new MessageListenerConcurrently() {

            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                // System.out.printf(Thread.currentThread().getName() + " Receive New Messages: " + msgs + "%n");
                for (MessageExt msg : msgs) {
                    try {
                        String msgStr = new String(msg.getBody(), RemotingHelper.DEFAULT_CHARSET);
                        System.out.println(msgStr);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
        System.out.printf("Broadcast ConsumerA Started.%n");
    }
}
