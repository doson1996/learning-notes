package com.ds.mybatisx.spring.mapper;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import java.util.List;

/**
 * @author ds
 * @date 2023/11/21
 * @description
 */
public class MapperxScannerConfigurer implements BeanDefinitionRegistryPostProcessor {

    public MapperxScannerConfigurer() {

    }

    private String basePackage;

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        System.out.println("basePackage = " + this.basePackage);
        ClassPathMapperxScanner classPathMapperxScanner = new ClassPathMapperxScanner(beanDefinitionRegistry);
        classPathMapperxScanner.scan(basePackage);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
