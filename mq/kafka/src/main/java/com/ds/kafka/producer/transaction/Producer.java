package com.ds.kafka.producer.transaction;

import com.ds.kafka.base.AbstractProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.ProducerFencedException;

import java.util.Properties;

/**
 * @author ds
 */
@Slf4j
public class Producer extends AbstractProducer {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        KafkaProducer<String, Object> producer = Producer.class.newInstance().initKafkaProducer();
        producer.initTransactions();
        try {
            producer.beginTransaction();
            for (int i = 0; i < 10; i++) {
                ProducerRecord<String, Object> record = new ProducerRecord<>(TOPIC, "hello kafka trx msg-" + i);
                producer.send(record, ((metadata, e) -> {
                    if (e != null) {
                        log.info("消息发送失败: " + e.getMessage());
                    } else {
                        log.info("消息发送成功：{}", record);
                    }
                }));
            }
            producer.commitTransaction();
        } catch (ProducerFencedException e) {
            log.error("发送事务消息异常: {}" + e.getMessage());
           // producer.close();
        }
        producer.close();
    }

    @Override
    public void put(Properties properties) {
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "trxId-2");
    }

}
