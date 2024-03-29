package com.ds.basic.dynamicproxy.spring.d2;

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

    @Before("execution(* foo())")
    public void before() {
        System.out.println("Aspect1 before...");
    }

    @After("execution(* foo())")
    public void after() {
        System.out.println("Aspect1 after...");
    }

}
