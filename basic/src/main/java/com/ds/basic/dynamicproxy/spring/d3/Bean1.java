package com.ds.basic.dynamicproxy.spring.d3;

import javax.annotation.PostConstruct;

/**
 * @author ds
 * @date 2024/1/22
 * @description
 */
public final class Bean1 implements IBean {

    @Override
    public void foo() {
        System.out.println("bean1 foo...");
    }

    //  @Autowired
    public void setBean2(Bean2 bean2) {
        System.out.println("setBean2 = " + bean2.getClass());
    }

    public Bean1() {
        System.out.println("bean1 constructor");
    }

    @PostConstruct
    public void init() {
        System.out.println("bean1 init");
    }
}
