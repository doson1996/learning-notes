package com.ds.springframework.chapte01.configuration;

import com.ds.springframework.chapte01.enable.EnableA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ds
 * @date 2024/4/12
 * @description
 */
@EnableA(path = "/usr/local")
@ComponentScan
@Configuration
public class AppConfig {

    @Bean
    public A a() {
        return new A();
    }

    @Bean
    public B b() {
        a();
        return new B();
    }

}
