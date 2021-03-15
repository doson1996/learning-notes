package com.ds.dp.prototype.sample;

/**
 * @Author ds
 * @Date 2021/3/15 11:46
 * @Description
 */
public class ConcretePrototype2 implements Prototype{

    @Override
    public Prototype clone() {
        Prototype prototype = new ConcretePrototype2();
        return prototype;
    }
}
