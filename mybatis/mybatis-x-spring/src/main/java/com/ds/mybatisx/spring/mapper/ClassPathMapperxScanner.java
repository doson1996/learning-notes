package com.ds.mybatisx.spring.mapper;

import com.ds.mybatisx.spring.annotion.Mapperx;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.classreading.MetadataReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.Set;

/**
 * @author ds
 * @date 2023/11/21
 * @description
 */
public class ClassPathMapperxScanner extends ClassPathBeanDefinitionScanner {
    public ClassPathMapperxScanner(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        if (beanDefinitionHolders.isEmpty()) {
            System.err.println(Arrays.toString(basePackages) + "未扫描到mapper...");
        } else {
            // 处理扫描处理的mapper
            processBeanDefinitions(beanDefinitionHolders);
        }
        return beanDefinitionHolders;
    }

    private void processBeanDefinitions(Set<BeanDefinitionHolder> beanDefinitions) {
        for (BeanDefinitionHolder holder : beanDefinitions) {
            BeanDefinition beanDefinition = holder.getBeanDefinition();
            String mapperClassName = beanDefinition.getBeanClassName();
            beanDefinition.setBeanClassName(MapperxFactoryBean.class.getName());

            MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
            propertyValues.addPropertyValue("mapperInterface", mapperClassName);
        }
    }

    @Override
    protected boolean isCandidateComponent(MetadataReader metadataReader) throws IOException {
        return metadataReader.getClassMetadata().isInterface();
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().hasAnnotation(Mapperx.class.getName());
    }
}
