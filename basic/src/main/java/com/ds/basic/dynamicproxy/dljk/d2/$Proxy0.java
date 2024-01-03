package com.ds.basic.dynamicproxy.dljk.d2;

/**
 * @author ds
 * @date 2024/1/3
 * @description
 */
public class $Proxy0 implements ITarget {
    @Override
    public void say() {
        // 增强
        System.out.println("before...");

        // 执行目标方法
        new Target().say();
    }
}
