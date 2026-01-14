package com.ds.basic.dynamicproxy.spring.d3;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ds
 * @date 2024/1/28
 * @description
 */
@Component
public class B {

    @Autowired
    public B(A a) {
        System.out.println("B()");
    }

//    @Autowired
    public B(A a, IBean iBean) {
        System.out.println("B()");
    }

    //    @Autowired
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
