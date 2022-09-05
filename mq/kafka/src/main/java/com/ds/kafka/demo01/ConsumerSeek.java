package com.ds.kafka.demo01;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Collections;


/**
 * @author ds
 */
@Slf4j
public class ConsumerSeek extends Consumer {

    public static void main(String[] args) {
        KafkaConsumer<String, Object> consumer = getKafkaConsumer();
        consumer.assign(Collections.singletonList(new TopicPartition(TOPIC, 0)));
        consumer.seek(new TopicPartition(TOPIC, 0), 0);
        ConsumerRecords<String, Object> records = consumer.poll(50000L);

        for (ConsumerRecord<String, Object> record : records) {
            log.info("收到消息： {}", record);
        }
        consumer.close();
    }

}
