package com.ds.springframework.ciz.beans.factory.support;

import com.ds.springframework.ciz.beans.BeansException;
import com.ds.springframework.ciz.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author ds
 * @date 2025/1/16
 * @description
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        try {
            Class beanClass = beanDefinition.getBeanClass();
            if (ctor != null) {
                return beanClass.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            } else {
                return beanClass.getDeclaredConstructor().newInstance();
            }

        } catch (Exception e) {
            throw new BeansException("instantiate error", e);
        }
    }

}
