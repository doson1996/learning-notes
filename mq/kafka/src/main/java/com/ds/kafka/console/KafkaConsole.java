package com.ds.kafka.console;

import com.ds.kafka.config.Config;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ConsumerGroupListing;
import org.apache.kafka.clients.admin.ListConsumerGroupsResult;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

/**
 * @author ds
 */
@Slf4j
public class KafkaConsole implements Config {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKER_LIST);
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, CLIENT_ID);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID_CONFIG);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        AdminClient adminClient = AdminClient.create(properties);
        ListConsumerGroupsResult listConsumerGroupsResult = adminClient.listConsumerGroups();

        System.out.println("--------------- kafka console ---------------");
        System.out.println("---------------    topics     ---------------");
        Set<String> topics = adminClient.listTopics().names().get();
        for (String topic : topics) {
            System.out.println(topic);
        }

        System.out.println("---------------   consumers    ---------------");
        for (ConsumerGroupListing consumerGroupListing : listConsumerGroupsResult.all().get()) {
            System.out.println(consumerGroupListing.groupId());
        }
    }
}
