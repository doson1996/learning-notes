package com.ds.springframework.ciz.beans.factory;

import com.ds.springframework.ciz.beans.BeansException;

/**
 * @author ds
 * @date 2024/12/5
 * @description
 */
public interface BeanFactory {

    /**
     * 根据beanName获取bean
     *
     * @param name
     * @return
     * @throws BeansException
     */
    Object getBean(String name) throws BeansException;

    /**
     * 根据beanName获取bean,允许指定显式构造函数参数/工厂方法参数
     *
     * @param name
     * @param args
     * @return
     * @throws BeansException
     */
    Object getBean(String name, Object... args) throws BeansException;

}
