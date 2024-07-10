package com.ds.springframework.chapter01.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author ds
 * @date 2024/7/8
 * @description
 */
@Component
public class LiteConfig {

    @Bean
    public BeanA getBeanA1() {
        System.out.println("getBeanA1...");
        return new BeanA();
    }

    @Bean
    public BeanB getBeanB1() {
        BeanB beanB = new BeanB();
        BeanA beanA1 = getBeanA1();
        beanB.setBeanA(beanA1);
        return beanB;
    }

}
