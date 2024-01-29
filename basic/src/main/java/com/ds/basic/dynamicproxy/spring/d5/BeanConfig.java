package com.ds.basic.dynamicproxy.spring.d5;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ds
 * @date 2024/1/29
 * @description
 */
@Configuration
public class BeanConfig {

    @Bean
    public Aspect1 aspect1() {
        return new Aspect1();
    }

    @Bean
    public Target target() {
        return new Target();
    }

}
