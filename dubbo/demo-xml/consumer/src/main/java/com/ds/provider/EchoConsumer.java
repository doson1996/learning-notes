package com.ds.provider;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author ds
 */
public class EchoConsumer {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo/consumer.xml");
        context.start();
    }

}
