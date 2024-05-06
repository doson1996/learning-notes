package com.ds.springframework.chapter01.lookup;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author ds
 * @date 2024/4/16
 * @description
 */
@Component
@Scope("singleton")
public class Single {

    @Resource
    private Prototype prototype;

    public Single() {
        System.out.println("Single构造方法");
    }

    public void say() {
        System.out.println(this);
        getPrototype().say();
        // prototype.say();
    }

    @Lookup
    public Prototype getPrototype() {
        return null;
    }

}
