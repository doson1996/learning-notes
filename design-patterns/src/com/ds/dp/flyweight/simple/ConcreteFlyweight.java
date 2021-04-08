package com.ds.dp.flyweight.simple;

/**
 * @Author ds
 * @Date 2021/4/6 9:59
 * @Description 享元对象
 */
public class ConcreteFlyweight implements Flyweight{

    private String innerState;

    public ConcreteFlyweight(String innerState) {
        this.innerState = innerState;
    }

    @Override
    public void operation(String extState) {
        // 具体的功能处理，可能会用到享元内部，外部状态
    }
}
