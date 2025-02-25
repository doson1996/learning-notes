package com.ds.dp.proxy.simple;

/**
 * @Author ds
 * @Date 2021/3/16 11:30
 * @Description 具体的目标对象，是真正被代理的对象
 */
public class RealSubject implements Subject {

    @Override
    public void request() {
        //执行具体的功能处理
        System.out.println("被代理对象执行---");
    }
}
