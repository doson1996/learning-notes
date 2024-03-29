package com.ds.mq.rocket.scheduled;

import cn.hutool.core.date.DateUtil;
import com.ds.mq.rocket.config.Config;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author ds
 */
public class Producer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("group_scheduled_producer");
        producer.setNamesrvAddr(Config.NAME_SERVER_ADDRESSES);
        producer.start();

        for (int i = 0; i < 10; i++) {
            Message message = new Message();
            message.setTopic("topic_scheduled");
            message.setTags("tag_scheduled");
            message.setKeys("key_scheduled");
            // 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h 对应着等级1到18
            message.setDelayTimeLevel(3);
            message.setBody(("延时消息" + i).getBytes(RemotingHelper.DEFAULT_CHARSET) );
            System.out.println(DateUtil.now()  + "发送延时消息" + i);

            producer.send(message);
            // 间隔一秒发送
            Thread.sleep(1000);
        }

        producer.shutdown();
    }

}
