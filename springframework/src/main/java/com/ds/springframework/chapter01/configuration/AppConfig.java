package com.ds.springframework.chapter01.configuration;

import com.ds.springframework.chapter01.enable.registrar.EnableCizScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ds
 * @date 2024/4/12
 * @description
 */
@EnableCizScan(path = "com.ds.springframework.chapter01.configuration")
@ComponentScan
@Configuration
public class AppConfig {

//    @Bean
//    public A a() {
//        return new A();
//    }
//
//    @Bean
//    public B b() {
//        a();
//        return new B();
//    }

}
