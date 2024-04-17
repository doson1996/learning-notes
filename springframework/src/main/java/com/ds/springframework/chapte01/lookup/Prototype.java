package com.ds.springframework.chapte01.lookup;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author ds
 * @date 2024/4/16
 * @description
 */
@Component
@Scope("prototype")
public class Prototype {

    public Prototype(){
        System.out.println("Prototype构造方法");
    }

    public void say() {
        System.out.println(this);
    }

}
