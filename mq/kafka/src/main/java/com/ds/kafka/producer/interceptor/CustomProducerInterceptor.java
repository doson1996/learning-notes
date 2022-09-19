package com.ds.kafka.producer.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * @author ds
 */
@Slf4j
public class CustomProducerInterceptor implements ProducerInterceptor<String, String> {

    int success;

    int fail;

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        String key = record.key();
        // key < 5 不发送
        if (Integer.parseInt(key) < 5) {
            return new ProducerRecord<>("", "");
        }
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (exception == null) {
            log.info("消息发送成功：{} - {}", metadata.topic(), metadata.offset());
            success++;
        } else {
            log.info("消息发送失败: " + exception.getMessage());
            fail++;
        }
    }

    @Override
    public void close() {
        log.info("success = {}", success);
        log.info("fail = {}", fail);
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }

}
