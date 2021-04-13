package com.ds.dp.bridge.simple;

/**
 * @Author ds
 * @Date 2021/4/13 15:58
 * @Description 真正的具体实现对象
 */
public class ConcreteImplementorB implements Implementor{

    @Override
    public void operationImpl() {
        System.out.println("ConcreteImplementorB");
    }
}
