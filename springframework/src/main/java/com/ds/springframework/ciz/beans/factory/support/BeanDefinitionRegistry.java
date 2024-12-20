package com.ds.springframework.ciz.beans.factory.support;

import com.ds.springframework.ciz.beans.BeansException;
import com.ds.springframework.ciz.beans.factory.config.BeanDefinition;

/**
 * @author ds
 * @date 2024/12/5
 * @description
 */
public interface BeanDefinitionRegistry {

    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

}
