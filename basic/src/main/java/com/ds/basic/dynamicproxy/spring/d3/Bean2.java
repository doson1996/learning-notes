package com.ds.basic.dynamicproxy.spring.d3;

import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * @author ds
 * @date 2024/1/22
 * @description
 */
public class Bean2 {

  //  @Autowired
    public void setBean1(Bean1 bean1) {
        System.out.println("setBean1 = " + bean1.getClass());
    }

    public Bean2() {
        System.out.println("bean2 constructor");
    }

    @PostConstruct
    public void init() {
        System.out.println("bean2 init");
    }

}
