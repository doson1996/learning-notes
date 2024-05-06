package com.ds.springframework.chapter01.lifecycle;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author ds
 * @date 2024/2/8
 * @description
 */
@Aspect
@Component
public class AspectConfig {

    @Pointcut("execution(* foo())")
    public void pointcut() {

    }

    @Before("pointcut()")
    public void before() {
        System.out.println("before...");
    }

}
