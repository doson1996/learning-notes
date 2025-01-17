package com.ds.springframework.ciz.beans.factory.support;

import com.ds.springframework.ciz.beans.BeansException;
import com.ds.springframework.ciz.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author ds
 * @date 2025/1/16
 * @description 实例化策略接口
 */
public interface InstantiationStrategy {

    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException;

}
