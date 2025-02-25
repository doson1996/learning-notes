package com.ds.dp.prototype.sample;

/**
 * @Author ds
 * @Date 2021/3/15 11:46
 * @Description
 */
public class ConcretePrototype1 implements Prototype {

    @Override
    public Prototype clone() {
        Prototype prototype = new ConcretePrototype1();
        return prototype;
    }
}
