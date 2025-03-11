package com.ds.mybatisx.spring.mapper;

import java.util.Arrays;
import java.util.Set;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;

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

    public void registerFilters() {
        // 把basePackages下所有接口都扫描出来
        addIncludeFilter((metadataReader, metadataReaderFactory) -> true);
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        //return  beanDefinition.getMetadata().hasAnnotation(Mapperx.class.getName());
        return beanDefinition.getMetadata().isInterface();
    }

}
