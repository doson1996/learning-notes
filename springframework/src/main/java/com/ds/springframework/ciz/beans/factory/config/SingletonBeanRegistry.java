package com.ds.springframework.ciz.beans.factory.config;

/**
 * @author ds
 * @date 2024/12/5
 * @description
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

}
