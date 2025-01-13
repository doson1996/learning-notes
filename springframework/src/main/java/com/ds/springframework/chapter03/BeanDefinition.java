package com.ds.springframework.chapter03;

/**
 * @author ds
 * @date 2024/11/16 0:39
 */
public class BeanDefinition {

    private Object bean;

    public BeanDefinition(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }

}
