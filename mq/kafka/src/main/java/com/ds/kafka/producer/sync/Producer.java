package com.ds.kafka.producer.sync;

import com.ds.kafka.base.AbstractProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.TimeUnit;

/**
 * 同步发送
 *
 * @author ds
 */
@Slf4j
public class Producer extends AbstractProducer {

    public static void main(String[] args) throws Exception {
        KafkaProducer<String, Object> producer = com.ds.kafka.producer.partitioner.Producer.class.newInstance().initKafkaProducer();
        ProducerRecord<String, Object> record = new ProducerRecord<>(TOPIC, "hello kafka sync");
        RecordMetadata recordMetadata = producer.send(record).get(5, TimeUnit.SECONDS);
        log.info("recordMetadata = {}", recordMetadata);
        producer.close();
    }

}
