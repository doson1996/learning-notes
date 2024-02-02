package com.ds.basic.dynamicproxy.spring.d3;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2024/1/28
 * @description
 */
public class A implements InitializingBean {

    public A() {
        System.out.println("A()");
    }

    @Resource
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
