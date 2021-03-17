package com.ds.dp.proxy.demo;

/**
 * @Author ds
 * @Date 2021/3/17 10:37
 * @Description
 */
public class Test {

    public static void main(String[] args) {

        Proxy proxy = new Proxy(new AServiceImpl());
        proxy.say();
    }
}
