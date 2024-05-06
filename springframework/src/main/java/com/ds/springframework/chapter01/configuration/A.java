package com.ds.springframework.chapter01.configuration;

/**
 * @author ds
 * @date 2024/4/12
 * @description
 */
// @Component
public class A {

    public A() {
        System.out.println("a...");
    }

    public String say(String msg) {
        System.out.println("msg = " + msg);
        return msg;
    }

}
