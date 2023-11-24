package com.ds.dubbo.provider.service;

import com.ds.dubbo.provider.stub.HelloService;

/**
 * @author ds
 * @date 2023/11/24
 * @description
 */
public class HelloServiceImplV1 implements HelloService {

    @Override
    public String sayHi(String name) {
        String msg = "hi, " + name + ". I am provider 1.";
        System.out.println("msg = " + msg);
        return msg;
    }

}
