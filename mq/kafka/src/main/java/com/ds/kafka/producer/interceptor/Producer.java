package com.ds.kafka.producer.interceptor;

import com.ds.kafka.base.AbstractProducer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author ds
 */
public class Producer extends AbstractProducer {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        KafkaProducer<String, Object> producer = Producer.class.newInstance().initKafkaProducer();
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, Object> record = new ProducerRecord<>(TOPIC, "hello kafka" + i);
            producer.send(record);
        }
        producer.close();
    }

    @Override
    public void put(Properties properties) {
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, MyProducerInterceptor.class.getName());
    }

    @Override
    public void remove(Properties properties) {
       // properties.remove(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG);
    }

}
