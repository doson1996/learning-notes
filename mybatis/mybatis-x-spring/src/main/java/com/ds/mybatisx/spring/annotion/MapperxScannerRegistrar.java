package com.ds.mybatisx.spring.annotion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.ds.mybatisx.spring.mapper.MapperxScannerConfigurer;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

/**
 * @author ds
 * @date 2023/11/21
 * @description
 */
public class MapperxScannerRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        // 获取注解
        AnnotationAttributes mapperxScanAttrs = AnnotationAttributes
                .fromMap(importingClassMetadata.getAnnotationAttributes(MapperxScan.class.getName()));
        if (mapperxScanAttrs != null) {
            BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(MapperxScannerConfigurer.class);
            List<String> basePackages = new ArrayList<>();
            basePackages.addAll(Arrays.stream(mapperxScanAttrs.getStringArray("value"))
                    .filter(StringUtils::hasText).collect(Collectors.toList()));
            basePackages.addAll(Arrays.stream(mapperxScanAttrs.getStringArray("basePackages"))
                    .filter(StringUtils::hasText).collect(Collectors.toList()));

            builder.addPropertyValue("basePackage", StringUtils.collectionToCommaDelimitedString(basePackages));
            registry.registerBeanDefinition("mapperxScannerConfigurer", builder.getBeanDefinition());
        }

    }
}
