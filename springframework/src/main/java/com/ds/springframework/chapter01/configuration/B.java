package com.ds.springframework.chapter01.configuration;

import com.ds.springframework.chapter01.enable.registrar.Ciz;

/**
 * @author ds
 * @date 2024/4/12
 * @description
 */
@Ciz
public class B {

    public String say(String msg) {
        System.out.println("msg = " + msg);
        return msg;
    }

}
