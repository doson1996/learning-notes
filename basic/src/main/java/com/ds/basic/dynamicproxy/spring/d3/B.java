package com.ds.basic.dynamicproxy.spring.d3;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author ds
 * @date 2024/1/28
 * @description
 */
public class B {

    public B() {
        System.out.println("B()");
    }

    @Resource
    private void setA(A a) {
        System.out.println("setA = " + a.getClass());
    }

    public void foo() {
        System.out.println("B foo...");
    }

    @PostConstruct
    public void init() {
        System.out.println("B init..." + this.getClass());
    }

}
