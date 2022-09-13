package com.ds.kafka.comsumer.interceptor;

import com.ds.kafka.base.AbstractConsumer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author ds
 */
@Slf4j
public class Consumer extends AbstractConsumer {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        KafkaConsumer<String, Object> consumer = Consumer.class.newInstance().initKafkaConsumer();
        consumer.subscribe(Collections.singletonList(TOPIC));
        ConsumerRecords<String, Object> records = consumer.poll(Duration.ofMillis(3000));
        for (ConsumerRecord<String, Object> record : records) {
            log.info("收到消息： {}", record);
        }
        consumer.close();
    }

    @Override
    public void put(Properties properties) {
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "GID_INTERCEPTOR");
        properties.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, MyProducerInterceptor.class.getName());
    }
}
