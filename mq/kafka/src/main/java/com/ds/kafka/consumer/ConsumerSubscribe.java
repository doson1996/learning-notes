package com.ds.kafka.consumer;

import com.ds.kafka.base.AbstractConsumer;
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
public class ConsumerSubscribe extends AbstractConsumer {

    public static void main(String[] args) {
        KafkaConsumer<String, Object> consumer = new ConsumerSubscribe().initKafkaConsumer();
        consumer.subscribe(Collections.singletonList(TOPIC));
        ConsumerRecords<String, Object> records = consumer.poll(50000L);

        for (ConsumerRecord<String, Object> record : records) {
            log.info("收到消息： {}", record);
        }
        consumer.close();
    }

    @Override
    public void put(Properties properties) {
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_CONFIG);
        // 新的消费者组，或者kafka server中的数据已过期，从最早的位移开始消费
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    }

}
