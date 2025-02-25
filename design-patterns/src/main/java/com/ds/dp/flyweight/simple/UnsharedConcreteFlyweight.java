package com.ds.dp.flyweight.simple;

/**
 * @Author ds
 * @Date 2021/4/6 9:59
 * @Description 不需要共享享元对象
 */
public class UnsharedConcreteFlyweight implements Flyweight {

    private String allState;


    @Override
    public void operation(String extState) {
        // 具体的功能处理
    }
}
