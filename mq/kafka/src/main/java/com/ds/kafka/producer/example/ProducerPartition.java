package com.ds.kafka.producer.example;

import com.ds.kafka.base.AbstractProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * 指定分区
 * @author ds
 */
@Slf4j
public class ProducerPartition extends AbstractProducer {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        KafkaProducer<String, Object> producer = ProducerPartition.class.newInstance().initKafkaProducer();
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, Object> record = new ProducerRecord<>(TOPIC, 1, "key", "hello kafka" + i);
            producer.send(record, ((metadata, exception) -> {
                if (exception == null) {
                    log.info("发送成功--> {} , partition: {}, offset: {}", record.value(), metadata.partition(), metadata.offset());
                } else {
                    log.error("发送失败--> {}", exception.getMessage());
                }
            }));
        }
        producer.close();
    }

}
