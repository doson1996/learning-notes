package com.ds.basic.dynamicproxy.spring.d4;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
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

    @AfterReturning("execution(* foo())")
    public void afterReturning() {
        System.out.println("Aspect1 afterReturning...");
    }

    @Around("execution(* foo())")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("Aspect1 around1...");
        Object result = point.proceed();
        System.out.println("Aspect1 around2...");
        return result;
    }

}
