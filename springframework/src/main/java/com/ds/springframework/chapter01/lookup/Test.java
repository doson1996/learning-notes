package com.ds.springframework.chapter01.lookup;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ds
 * @date 2024/4/16
 * @description
 */
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        for (int i = 0; i < 10; i++) {
            Single single = context.getBean(Single.class);
            single.say();
        }

    }
}
