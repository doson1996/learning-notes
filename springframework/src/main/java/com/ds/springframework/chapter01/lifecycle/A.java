package com.ds.springframework.chapter01.lifecycle;

import org.springframework.stereotype.Component;

/**
 * @author ds
 * @date 2024/2/8
 * @description
 */
@Component
public class A {

    static {
        System.out.println("static a...");
    }

    public void say() {
        System.out.println("foo...");
    }

}
