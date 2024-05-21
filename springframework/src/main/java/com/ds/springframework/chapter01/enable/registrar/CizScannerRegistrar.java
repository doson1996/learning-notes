package com.ds.springframework.chapter01.enable.registrar;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @author ds
 * @date 2024/4/12
 * @description
 */
public class CizScannerRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(EnableCizScan.class.getName());
        assert annotationAttributes != null;
        String path = (String) annotationAttributes.get("path");
        BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(CizScannerConfigurer.class);
        builder.addPropertyValue("path", path);
        registry.registerBeanDefinition("cizScannerConfigurer", builder.getBeanDefinition());
    }

}
