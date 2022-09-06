package com.ds.kafka.demo01;

import com.ds.kafka.config.Config;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author ds
 */
@Slf4j
public class Producer implements Config {

    public static void main(String[] args) {
        KafkaProducer<String, Object> producer = getKafkaProducer();
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, Object> record = new ProducerRecord<>(TOPIC, "hello kafka" + i);
            producer.send(record, ((recordMetadata, e) -> {
                if (e != null) {
                    log.info("消息发送失败: " + e.getMessage());
                } else {
                    log.info("消息发送成功：{}", record);
                }
            }));
        }
        producer.close();
    }

    private static KafkaProducer<String, Object> getKafkaProducer() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, CLIENT_ID);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return new KafkaProducer<>(properties);
    }

}
