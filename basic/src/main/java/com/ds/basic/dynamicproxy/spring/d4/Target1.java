package com.ds.basic.dynamicproxy.spring.d4;

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

    @Override
    public void bar() {
        System.out.println("bar...");
    }

}
