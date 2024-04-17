package com.ds.springframework.chapte01.enable.registrar;

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
        Object o = annotationAttributes.get("path");
        System.out.println("annotationAttributes = " + annotationAttributes);
    }

}
