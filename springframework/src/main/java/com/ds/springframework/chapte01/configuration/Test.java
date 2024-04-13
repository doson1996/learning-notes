package com.ds.springframework.chapte01.configuration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ds
 * @date 2024/4/12
 * @description 带上@Configuration注解的配置类称之为Full配置类，不带的称之为Lite配置类
 *              Cglib会代理Full配置类
 */
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        A a = context.getBean(A.class);
        a.say("hello");

        AppConfig appConfig = context.getBean(AppConfig.class);
        System.out.println("appConfig = " + appConfig.getClass());
    }
}
