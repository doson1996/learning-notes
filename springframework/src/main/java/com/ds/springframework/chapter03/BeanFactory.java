package com.ds.springframework.chapter03;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ds
 * @date 2024/11/16 0:45
 */
public class BeanFactory {

    Map<String, BeanDefinition> beanDefinitions = new HashMap<>();

    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitions.put(beanName, beanDefinition);
    }

    public Object getBean(String beanName) {
        return beanDefinitions.get(beanName).getBean();
    }

}
