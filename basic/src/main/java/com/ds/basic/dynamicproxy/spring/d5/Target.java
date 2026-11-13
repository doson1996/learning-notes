package com.ds.basic.dynamicproxy.spring.d5;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ds
 * @date 2024/1/29
 * @description
 */
public class Target implements InitializingBean {

    @Resource
    private A a;

//    @PostConstruct
//    public void init() {
//        System.out.println("init..");
//    }


    public Target() {
        System.out.println("Target构造方法..");
    }

//    @Autowired
//    public void setA(A a) {
//        System.out.println("setA a = " + a);
//        this.a = a;
//    };

    public void foo(int i) {
        System.out.println("foo a = " + i + this.a);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Target init.." + this);
    }

//    @Override
//    public String toString() {
//        return "Target{" +
//                "a=" + a +
//                '}';
//    }

}
