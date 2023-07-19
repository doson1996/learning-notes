package com.ds.netty.chapter02netty.d9rpc.service;

/**
 * @author ds
 * @date 2023/7/19
 * @description
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return  "服务端ROBOT ：你好, " + name;
    }
}
