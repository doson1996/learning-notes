package com.ds.basic.dynamicproxy.spring.d3;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ds
 * @date 2024/1/28
 * @description
 */
@Component
public class A implements InitializingBean {

//    @Autowired
//    public A(B b) {
//        System.out.println("A()");
//    }

//    @Autowired
    private void setB(B b) {
        System.out.println("setB = " + b.getClass());
    }

    public void foo() {
        System.out.println("A foo...");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("A init..." + this.getClass());
    }
}
