package com.ds.springframework.chapter01.enable.registrar;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

import java.util.Set;

/**
 * @author ds
 * @date 2024/5/20
 * @description
 */
public class CizScanner extends ClassPathBeanDefinitionScanner {

    public CizScanner(BeanDefinitionRegistry registry) {
        super(registry, false);
    }

    @Override
    protected Set<BeanDefinitionHolder> doScan(String... basePackages) {
        // 可以对扫描出来的 bd做处理，
        Set<BeanDefinitionHolder> beanDefinitionHolders = super.doScan(basePackages);
        return beanDefinitionHolders;
    }

    public void registerFilters() {
        TypeFilter annotationTypeFilter = new AnnotationTypeFilter(Ciz.class);
        addIncludeFilter(annotationTypeFilter);
    }

    @Override
    protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
        return beanDefinition.getMetadata().hasAnnotation(Ciz.class.getName());
    }

}
