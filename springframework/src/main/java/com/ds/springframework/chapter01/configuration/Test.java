package com.ds.springframework.chapter01.configuration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ds
 * @date 2024/4/12
 * @description 带上@Configuration注解的配置类称之为Full配置类，不带的称之为Lite配置类
 * Cglib会代理Full配置类
 */
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class, FullConfig.class);

        FullConfig fullConfig = context.getBean(FullConfig.class);
        System.out.println("fullConfig = " + fullConfig.getClass());

        LiteConfig liteConfig = context.getBean(LiteConfig.class);
        System.out.println("liteConfig = " + liteConfig);

        BeanA a = context.getBean("getBeanA",BeanA.class);
        System.out.println("a = " + a.getClass());

//        A a = context.getBean(A.class);
//        a.say("hello a");
//
//        B b = context.getBean(B.class);
//        b.say("hello b");
//
//        AppConfig appConfig = context.getBean(AppConfig.class);
//        System.out.println("appConfig = " + appConfig.getClass());
    }
}
