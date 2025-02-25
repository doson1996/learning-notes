package com.ds.basic.dynamicproxy.spring.d2;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ds
 * @date 2024/1/22
 * @description
 */
@Configuration
public class Config {

    @Bean
    public MethodInterceptor advice2() {
        return invocation -> {
            System.out.println("advice2 before...");
            Object result = invocation.proceed();
            System.out.println("advice2 after...");
            return result;
        };
    }

    @Bean
    public Advisor advisor2(MethodInterceptor advice2) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* foo())");
        return new DefaultPointcutAdvisor(pointcut, advice2);
    }

}
