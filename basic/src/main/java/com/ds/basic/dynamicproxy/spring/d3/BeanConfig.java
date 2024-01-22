package com.ds.basic.dynamicproxy.spring.d3;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ds
 * @date 2024/1/22
 * @description
 */
@Configuration
public class BeanConfig {

    @Bean
    public Bean1 bean1() {
        return new Bean1();
    }

    @Bean
    public Bean2 bean2() {
        return new Bean2();
    }

}
