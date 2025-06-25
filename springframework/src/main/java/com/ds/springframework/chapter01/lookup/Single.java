package com.ds.springframework.chapter01.lookup;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author ds
 * @date 2024/4/16
 * @description @Lookup 每次获取不同@Scope("prototype")的bean
 *              @Resource 每次获取的是同一个bean
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
        System.out.println("------");
        getPrototype().say();
        getPrototype().say();
        System.out.println("------");
        prototype.say();
        prototype.say();
    }

    @Lookup
    public Prototype getPrototype() {
        return null;
    }

}
