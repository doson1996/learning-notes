package com.ds.basic.dynamicproxy.spring.d5;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ds
 * @date 2024/1/29
 * @description
 */
public class Target {

    @Autowired
    private A a;

    @Autowired
    public void setA(A a) {
        System.out.println("a = " + a);
        this.a = a;
    };

    public void foo(int i) {
        System.out.println("foo i = " + i + this.a);
    }

}
