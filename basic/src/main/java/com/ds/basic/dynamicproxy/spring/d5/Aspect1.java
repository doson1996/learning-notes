package com.ds.basic.dynamicproxy.spring.d5;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * @author ds
 * @date 2024/1/22
 * @description 高级切面
 */
@Aspect
public class Aspect1 {

    @Before("execution(* foo(..))")             //静态调用，不需要切点
    public void before1() {
        System.out.println("Aspect1 before1...");
    }

    @Before("execution(* foo(..)) && args(i)")  // 动态调用，需要参数绑定和切点对象
    public void before2(int i) {
        System.out.println("Aspect1 before2..." + i);
    }

    @After("execution(* foo(..))")
    public void after() {
        System.out.println("Aspect1 after...");
    }

}
