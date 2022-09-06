package com.ds.kafka.producer.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * 实现ProducerInterceptor接口，你可以在生产者发布到Kafka群集
 * 之前拦截（也可变更）生产者收到的消息。默认情况下没有拦截器。
 *
 * @author ds
 */
@Slf4j
public class MyProducerInterceptor implements ProducerInterceptor {
    @Override
    public ProducerRecord onSend(ProducerRecord producerRecord) {
        log.info("ProducerExt onSend {}", producerRecord);
        return producerRecord;
    }

    @Override
    public void onAcknowledgement(RecordMetadata recordMetadata, Exception e) {
        if (e != null) {
            log.info("消息发送失败: " + e.getMessage());
        } else {
            log.info("消息发送成功：{} - {}", recordMetadata.topic(), recordMetadata.offset());
        }
    }

    @Override
    public void close() {
        log.info("ProducerExt close");
    }

    @Override
    public void configure(Map<String, ?> map) {
        log.info("ProducerExt configure {}", map);
    }
}
