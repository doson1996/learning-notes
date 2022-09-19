package com.ds.kafka.producer.interceptor;

import com.ds.kafka.base.AbstractProducer;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.Properties;

/**
 * @author ds
 */
@Slf4j
public class CustomInterceptorProducer extends AbstractProducer {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        KafkaProducer<String, Object> producer = CustomInterceptorProducer.class.newInstance().initKafkaProducer();
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, Object> record = new ProducerRecord<>(TOPIC, i + "", "hello kafka msg" + i);
            producer.send(record);
        }
        producer.close();
    }

    @Override
    public void put(Properties properties) {
        // 添加多个拦截器
        ArrayList<String> interceptors = new ArrayList<>();
        interceptors.add(CustomProducerInterceptor.class.getName());
        interceptors.add(MyProducerInterceptor.class.getName());
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, interceptors);
    }

}
