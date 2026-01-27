package com.ds.basic.dynamicproxy.spring.d3;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author ds
 * @date 2024/1/22
 * @description 高级切面
 */
@Aspect
@Component
public class Aspect2 {

    @Before("execution(* foo())")
    public void before() {
        System.out.println("Aspect2 before...");
    }

    @After("execution(* foo())")
    public void after() {
        System.out.println("Aspect2 after...");
    }

}
