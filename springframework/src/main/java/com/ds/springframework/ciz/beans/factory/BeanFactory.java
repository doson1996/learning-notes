package com.ds.springframework.ciz.beans.factory;

import com.ds.springframework.ciz.beans.BeansException;

/**
 * @author ds
 * @date 2024/12/5
 * @description
 */
public interface BeanFactory {

    Object getBean(String name) throws BeansException;

}
