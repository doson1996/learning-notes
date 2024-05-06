package com.ds.springframework.chapter01.bean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserServiceFactoryBean implements FactoryBean<UserService>, InitializingBean {

    @Override
    public UserService getObject() throws Exception {
        return (UserService) Proxy.newProxyInstance(UserServiceFactoryBean.class.getClassLoader(), new Class[]{UserService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("method = " + method.getName());
                if (Object.class.equals(method.getDeclaringClass())) {
                    return method.invoke(this, args);
                }

                return "1";
            }
        });
    }

    @Override
    public Class<UserService> getObjectType() {
        return UserService.class;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("UserServiceFactoryBean...");
    }
}
