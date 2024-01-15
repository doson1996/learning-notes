package com.ds.basic.dynamicproxy.dljdk.d2;

/**
 * @author ds
 * @date 2024/1/3
 * @description
 */
public class Target implements ITarget {
    @Override
    public void say() {
        System.out.println("say...");
    }
}
