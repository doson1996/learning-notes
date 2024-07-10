package com.ds.springframework.chapter01.configuration;

import lombok.Data;

/**
 * @author ds
 * @date 2024/7/8
 * @description
 */
@Data
public class BeanB {

    public BeanB() {
        System.out.println("BeanB");
    }

    private BeanA beanA;

}
