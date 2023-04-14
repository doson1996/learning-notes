package com.ds.basic.dynamicproxy.base.test;

/**
 * @author ds
 * @date 2023/4/14
 * @description
 */
public class UserServiceImpl implements IUserService {
    @Override
    public void say() {
        System.out.println("say...");
    }
}
