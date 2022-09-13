package com.ds.kafka.comsumer.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.Map;

/**
 * @author ds
 */
@Slf4j
public class MyProducerInterceptor implements ConsumerInterceptor<String, Object> {
    @Override
    public ConsumerRecords<String, Object> onConsume(ConsumerRecords<String, Object> records) {
        log.info("onConsume 消费到{}条数据", records.count());
        return records;
    }

    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {
        log.info("onCommit...");
    }

    @Override
    public void close() {
        log.info("close...");
    }

    @Override
    public void configure(Map<String, ?> configs) {
        log.info("configure...");
    }
}
