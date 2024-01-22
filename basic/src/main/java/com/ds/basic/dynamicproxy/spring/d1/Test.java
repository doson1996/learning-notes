package com.ds.basic.dynamicproxy.spring.d1;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.core.annotation.MergedAnnotations;

import java.lang.reflect.Method;

/**
 * @author ds
 * @date 2024/1/21
 * @description
 */
public class Test {
    public static void main(String[] args) throws Exception {
        // 1.准备切点
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* foo())");

        // 切点匹配
        System.out.println(pointcut.matches(Target1.class.getMethod("foo"), Target1.class));
        System.out.println(pointcut.matches(Target1.class.getMethod("bar"), Target1.class));
        System.out.println("------------------切点匹配-------------------------");

        // 切注解
        AspectJExpressionPointcut pointcut1 = new AspectJExpressionPointcut();
        pointcut1.setExpression("@annotation(com.ds.basic.dynamicproxy.spring.d1.Aop)");
        System.out.println(pointcut1.matches(Target1.class.getMethod("foo"), Target1.class));
        System.out.println(pointcut1.matches(Target1.class.getMethod("bar"), Target1.class));
        System.out.println("--------------------切注解-----------------------");

        StaticMethodMatcherPointcut pointcut2 = new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(Method method, Class<?> clazz) {
                // 匹配方法上有没有Aop注解
                MergedAnnotations annotations = MergedAnnotations.from(method);
                if (annotations.isPresent(Aop.class)) {
                    return true;
                }

                // 匹配类以及接口上有没有Aop注解
                annotations = MergedAnnotations.from(clazz, MergedAnnotations.SearchStrategy.TYPE_HIERARCHY);
                if (annotations.isPresent(Aop.class)) {
                    return true;
                }

                return false;
            }
        };

        System.out.println(pointcut2.matches(Target1.class.getMethod("foo"), Target1.class));
        System.out.println(pointcut2.matches(Target1.class.getMethod("bar"), Target1.class));
        // 实现接口带有Aop注解
        System.out.println(pointcut2.matches(A.class.getMethod("say"), A.class));
        System.out.println("--------------------切注解-----------------------");

        // 2.准备通知
        MethodInterceptor advice = new MethodInterceptor() {
            @Override
            public Object invoke(MethodInvocation invocation) throws Throwable {
                System.out.println("before...");
                Object result = invocation.proceed();
                System.out.println("after...");
                return result;
            }
        };

        // 3.准备切面
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);

        // 4.创建代理
        /*
            1.proxyTargetClass = false, 目标实现接口，使用jdk
            2.proxyTargetClass = false, 目标没实现接口，使用cglib
            3.proxyTargetClass = true, 总使用cglib
         */
        Target1 target1 = new Target1();
        Target2 target2 = new Target2();
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.addAdvisors(advisor);
        proxyFactory.setProxyTargetClass(false);

        // 2.proxyTargetClass = false, 目标没实现接口，使用cglib
        proxyFactory.setTarget(target2);
        Target2 proxy2 = (Target2) proxyFactory.getProxy();
        System.out.println("proxy2 = " + proxy2.getClass());

        // 1.proxyTargetClass = false, 目标实现接口，使用jdk
        proxyFactory.setTarget(target1);
        proxyFactory.setInterfaces(target1.getClass().getInterfaces());
        ITarget proxy1 = (ITarget) proxyFactory.getProxy();
        System.out.println("proxy1 = " + proxy1.getClass());
        proxy1.foo();
        proxy1.bar();

        //  3.proxyTargetClass = true, 总使用cglib
        proxyFactory.setTarget(target1);
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.setInterfaces(target1.getClass().getInterfaces());
        ITarget proxy3 = (ITarget) proxyFactory.getProxy();
        System.out.println("proxy3 = " + proxy3.getClass());
    }
}
