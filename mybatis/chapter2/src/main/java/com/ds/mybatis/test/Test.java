package com.ds.mybatis.test;

import com.ds.mybatis.binding.MapperProxyFactory;
import com.ds.mybatis.dao.UserDao;

import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ds
 * @date 2025/6/18
 * @description
 */
public class Test {

    public static void main(String[] args) {
        test2();
    }

    public static void test2() {
        Map<String, String> sqlSession = new HashMap<>();
        sqlSession.put("com.ds.mybatis.dao.UserDao.queryName", "select name from user");

        MapperProxyFactory<UserDao> mapperProxy = new MapperProxyFactory<>(UserDao.class);

        UserDao userDao = mapperProxy.newInstance(sqlSession);
        String name = userDao.queryName();
        System.out.println("name = " + name);
    }

    public static void test1() {
        UserDao userDao = (UserDao) Proxy.newProxyInstance(Test.class.getClassLoader(), new Class[]{UserDao.class}, (proxy, method, args) -> {
            return "张三";
        });

        String name = userDao.queryName();
        System.out.println("name = " + name);
    }

}
