package com.ds.kafka.consumer.offset;

import com.ds.kafka.base.AbstractConsumer;
import com.ds.kafka.consumer.ConsumerSubscribe;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Collections;
import java.util.Properties;

/**
 * @author ds
 */
@Slf4j
public class Consumer extends AbstractConsumer {

    public static void main(String[] args) {
        KafkaConsumer<String, Object> consumer = new Consumer().initKafkaConsumer();
        consumer.subscribe(Collections.singletonList(TOPIC));
        ConsumerRecords<String, Object> records = consumer.poll(5000L);

        for (ConsumerRecord<String, Object> record : records) {
            log.info("收到消息： {}", record);
        }
        consumer.close();
    }

    @Override
    public void put(Properties properties) {
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_CONFIG);
        //关闭自动提交offset
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
    }

}
