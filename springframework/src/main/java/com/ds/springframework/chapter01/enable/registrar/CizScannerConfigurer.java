package com.ds.springframework.chapter01.enable.registrar;

import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * @author ds
 * @date 2024/4/15
 * @description
 */
@Setter
public class CizScannerConfigurer implements BeanDefinitionRegistryPostProcessor {

    private String path;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        CizScanner scanner = new CizScanner(registry);
        scanner.registerFilters();
        scanner.scan(path);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

}
