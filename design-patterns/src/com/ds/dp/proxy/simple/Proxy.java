package com.ds.dp.proxy.simple;

/**
 * @Author ds
 * @Date 2021/3/16 11:32
 * @Description 代理对象
 */
public class Proxy implements Subject{

    /**
     * 持有被具体代理的具体目标对象
     */
    private RealSubject realSubject;

    public Proxy(RealSubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void request() {
        //执行之前可以执行一些方法
        System.out.println("执行之前---");

        //转调具体的目标对象方法
        realSubject.request();

        //执行之后可以执行一些方法
        System.out.println("执行之后---");
    }
}
