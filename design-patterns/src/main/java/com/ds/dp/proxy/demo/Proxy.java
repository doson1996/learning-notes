package com.ds.dp.proxy.demo;

/**
 * @Author ds
 * @Date 2021/3/17 10:31
 * @Description
 */
public class Proxy implements AService {

    AService aService;

    Proxy(AService aService) {
        this.aService = aService;
    }

    @Override
    public void say() {

        System.out.println("说之前做点什么--");

        aService.say();

        System.out.println("说之后做点什么--");
    }
}
