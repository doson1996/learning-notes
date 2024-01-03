package com.ds.basic.dynamicproxy.dljk.d2;

/**
 * @author ds
 * @date 2024/1/3
 * @description
 */
public class TestProxy {
    public static void main(String[] args) {
        ITarget target = new $Proxy0();
        target.say();
    }
}
