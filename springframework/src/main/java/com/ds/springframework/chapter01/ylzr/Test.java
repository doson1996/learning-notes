package com.ds.springframework.chapter01.ylzr;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ds
 * @date 2026/2/5
 * @description
 */
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        UserService userService = context.getBean(UserService.class);
        userService.say();
    }
}
