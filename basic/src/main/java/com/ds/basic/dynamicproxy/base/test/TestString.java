package com.ds.basic.dynamicproxy.base.test;

import java.io.Serializable;

import com.ds.basic.dynamicproxy.base.CglibProxy;
import com.ds.basic.dynamicproxy.base.JdkProxy;

/**
 * @author ds
 * @date 2024/1/3
 * @description
 */
public class TestString {
    public static void main(String[] args) {
        String str = new String("a");
        Serializable strProxy = (Serializable) new JdkProxy().create(str);
        System.out.println(strProxy);

        String s = (String) new CglibProxy().create(str);
        s.toString();
    }
}
