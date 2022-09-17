package com.ds.kafka.producer.partitioner;

import com.ds.kafka.base.AbstractProducer;
import com.ds.kafka.producer.interceptor.MyProducerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author ds
 */
@Slf4j
public class Producer extends AbstractProducer {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        KafkaProducer<String, Object> producer = Producer.class.newInstance().initKafkaProducer();
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, Object> record = new ProducerRecord<>(TOPIC, "hello kafka" + i);
            producer.send(record, ((metadata, exception) -> {
                if (exception == null) {
                    log.info("发送成功--> {} , partition: {}, offset: {}", record, metadata.partition(), metadata.offset());
                } else {
                    log.error("发送失败--> {}", exception.getMessage());
                }
            }));
        }
        producer.close();
    }

    @Override
    public void put(Properties properties) {
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, MyPartitioner.class.getName());
    }

    @Override
    public void remove(Properties properties) {
       // properties.remove(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG);
    }

}
