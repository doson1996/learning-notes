package com.ds.dp.bridge.simple;

/**
 * @Author ds
 * @Date 2021/4/13 15:58
 * @Description 真正的具体实现对象
 */
public class ConcreteImplementorA implements Implementor {

    @Override
    public void operationImpl() {
        System.out.println("ConcreteImplementorA");
    }
}
