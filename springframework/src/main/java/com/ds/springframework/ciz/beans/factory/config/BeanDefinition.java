package com.ds.springframework.ciz.beans.factory.config;

import java.lang.reflect.Constructor;

/**
 * @author ds
 * @date 2024/12/5
 * @description
 */
public class BeanDefinition {

    private Class beanClass;

    private Constructor ctor;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Constructor getCtor() {
        return ctor;
    }

    public void setCtor(Constructor ctor) {
        this.ctor = ctor;
    }

}
