package com.ds.dp.proxy.simple;

/**
 * @Author ds
 * @Date 2021/3/16 11:38
 * @Description 代理模式简单示例客户端
 */
public class Client {

    public static void main(String[] args) {

        Proxy proxy = new Proxy(new RealSubject());
        proxy.request();

    }
}
