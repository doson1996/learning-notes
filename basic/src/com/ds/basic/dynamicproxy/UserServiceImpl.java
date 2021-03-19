package com.ds.basic.dynamicproxy;

/**
 * @Author ds
 * @Date 2021/3/19 9:45
 * @Description
 */
public class UserServiceImpl implements UserService{

    @Override
    public void save() {
        System.out.println("执行userService save方法");
    }
}
