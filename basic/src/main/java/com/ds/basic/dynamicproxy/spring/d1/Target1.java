package com.ds.basic.dynamicproxy.spring.d1;

/**
 * @author ds
 * @date 2024/1/21
 * @description
 */
public class Target1 implements ITarget {

    @Override
    public void foo() {
        System.out.println("foo...");
    }

    @Aop
    @Override
    public void bar() {
        System.out.println("bar...");
    }

}
