package com.ds.basic.dynamicproxy.spring.d5;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ds
 * @date 2026/7/27
 * @description
 */
public class A implements InitializingBean {

    @Resource
    private Target target;

    public A() {
        System.out.println("A构造方法..");
    }

//    @Autowired
//    public void setTarget(Target target) {
//        this.target = target;
//    }

    public void foo(int i) {
        System.out.println("foo target = " + target);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("A init.." + this);
    }

//    @Override
//    public String toString() {
//        return "A{" +
//                "target=" + target +
//                '}';
//    }
}
