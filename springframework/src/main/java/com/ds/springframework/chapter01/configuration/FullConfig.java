package com.ds.springframework.chapter01.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ds
 * @date 2024/7/8
 * @description
 */
@Configuration
public class FullConfig {

    @Bean
    public BeanA getBeanA() {
        System.out.println("getBeanA...");
        return new BeanA();
    }

    @Bean
    public BeanB getBeanB() {
        BeanB beanB = new BeanB();
        BeanA beanA = getBeanA();
        beanB.setBeanA(beanA);
        return beanB;
    }

}
