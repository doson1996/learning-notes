package com.ds.basic.dynamicproxy.spring.d4;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.*;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ds
 * @date 2024/1/23
 * @description
 */
public class Test {
    public static void main(String[] args) throws Exception {
        List<Advisor> advisors = new ArrayList<>();

        AspectInstanceFactory factory = new SingletonAspectInstanceFactory(new Aspect1());

        for (Method method : Aspect1.class.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Before.class)) {
                // 解析切点
                Before before = method.getAnnotation(Before.class);
                AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
                pointcut.setExpression(before.value());
                // 通知类
                AspectJMethodBeforeAdvice advice = new AspectJMethodBeforeAdvice(method, pointcut, factory);
                // 切面
                Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
                advisors.add(advisor);
            }

            if (method.isAnnotationPresent(After.class)) {
                // 解析切点
                After before = method.getAnnotation(After.class);
                AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
                pointcut.setExpression(before.value());
                // 通知类
                AspectJAfterAdvice advice = new AspectJAfterAdvice(method, pointcut, factory);
                // 切面
                Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
                advisors.add(advisor);
            }

            if (method.isAnnotationPresent(Around.class)) {
                // 解析切点
                Around before = method.getAnnotation(Around.class);
                AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
                pointcut.setExpression(before.value());
                // 通知类
                AspectJAroundAdvice advice = new AspectJAroundAdvice(method, pointcut, factory);
                // 切面
                Advisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
                advisors.add(advisor);
            }

        }

        for (Advisor advisor : advisors) {
            System.out.println("advisor = " + advisor);
        }

        Target1 target1 = new Target1();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvisors(advisors);
        proxyFactory.setTarget(target1);

        List<Object> list = proxyFactory.getInterceptorsAndDynamicInterceptionAdvice(Target1.class.getMethod("foo"), Target1.class);
        for (Object o : list) {
            System.out.println("o = " + o);
        }
        // ITarget proxy = (ITarget) proxyFactory.getProxy();

        // proxy.foo();
    }
}
