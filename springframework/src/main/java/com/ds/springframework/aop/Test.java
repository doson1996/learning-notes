package com.ds.springframework.aop;

import org.springframework.aop.Advisor;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

/**
 * @author ds
 * @date 2025/5/29
 * @description
 */
public class Test {
    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory();

        // 一个Advisor代表的是一个已经跟指定切点绑定了的通知
        // 在这个例子中意味着环绕通知不会作用到toString方法上
        Advisor advisor = new DefaultPointcutAdvisor(new DsPointcut(), new AroundAdvice());

        // 添加一个绑定了指定切点的环绕通知
        proxyFactory.addAdvisor(advisor);

        // 添加一个返回后的通知
        proxyFactory.addAdvice(new AfterReturnAdvice());

        // 添加一个方法执行前的通知
        proxyFactory.addAdvice(new BeforeAdvice());

        // 为代理类引入一个新的需要实现的接口--IService
        proxyFactory.addAdvice(new IntroductionAdvice());

        // 设置目标类
        proxyFactory.setTarget(new DsService());

        // 因为要测试代理对象自己定义的方法，所以这里启用cglib代理
//        proxyFactory.setProxyTargetClass(true);

        // 创建代理对象
        Object proxy = proxyFactory.getProxy();

        // 调用代理类的toString方法，通过控制台查看代理逻辑的执行情况
//        proxy.toString();

//        if (proxy instanceof DsService) {
//            ((DsService) proxy).testAop();
//        }

        // 判断引入是否成功，并执行引入的逻辑
        if (proxy instanceof IService) {
            ((IService) proxy).say();
        }

//        proxyFactory.setTarget(IService.class);

    }
}
