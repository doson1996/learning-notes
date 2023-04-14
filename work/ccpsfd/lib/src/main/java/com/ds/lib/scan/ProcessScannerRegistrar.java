package com.ds.lib.scan;

import com.ds.lib.annotation.EnableBatchProcess;
import com.ds.lib.annotation.Process;
import com.ds.lib.batch.BaseProcessJob;
import com.ds.lib.batch.ProcessFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author ds
 * @date 2023/4/13
 * @description
 */
@Slf4j
public class ProcessScannerRegistrar implements ImportBeanDefinitionRegistrar, ResourceLoaderAware, EnvironmentAware {

    /**
     * 资源加载器
     */
    private ResourceLoader resourceLoader;

    /**
     * 环境
     */
    private Environment environment;

    public ProcessScannerRegistrar() {
        log.info("processScannerRegistrar...");
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        // 创建scanner
        ClassPathScanningCandidateComponentProvider scanner = getScanner();
        scanner.setResourceLoader(resourceLoader);

        // 设置扫描器scanner扫描的过滤条件
        AnnotationTypeFilter annotationTypeFilter = new AnnotationTypeFilter(Process.class);
        scanner.addIncludeFilter(annotationTypeFilter);

        // 获取指定要扫描的basePackages
        Set<String> basePackages = getBasePackages(metadata);

        // 遍历每一个basePackages
        for (String basePackage : basePackages) {
            // 通过scanner获取basePackage下的候选类(有标@Process注解的类)
            Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents(basePackage);
            // 遍历每一个候选类，如果符合条件就把他们注册到容器
            for (BeanDefinition candidateComponent : candidateComponents) {
                if (candidateComponent instanceof AnnotatedBeanDefinition) {
                    // todo 可以做一些校验
                    AnnotatedBeanDefinition beanDefinition = (AnnotatedBeanDefinition) candidateComponent;
                    AnnotationMetadata annotationMetadata = beanDefinition.getMetadata();
                    // Assert.isTrue(annotationMetadata.isInterface(), "@Process can only be specified on an interface");
                    // 获取@Process注解的属性
                    Map<String, Object> attributes = annotationMetadata.getAnnotationAttributes(Process.class.getCanonicalName());
                    // 注册到容器
                    registerProcess(registry, annotationMetadata, attributes);
                }
            }
        }
    }

    /**
     * 利用factoryBean创建代理对象，并注册到容器
     */
    private static void registerProcess(BeanDefinitionRegistry registry,
                                        AnnotationMetadata annotationMetadata,
                                        Map<String, Object> attributes) {
        // 类名（接口全限定名）
        String className = annotationMetadata.getClassName();
        // 创建ProcessFactoryBean的BeanDefinition
        BeanDefinitionBuilder definition = BeanDefinitionBuilder.genericBeanDefinition(ProcessFactoryBean.class);
        // 解析出@Process注解的name
        String name = getName(attributes);
        if (!StringUtils.hasText(name)) {
            throw new RuntimeException(String.format("class [%s] , @Process name or value can not be null, please check.", className));
        }

        // 给factoryBean添加属性值
        definition.addPropertyValue("name", name);
        definition.addPropertyValue("type", className);
        definition.setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
        String alias = name + "Process";
        AbstractBeanDefinition beanDefinition = definition.getBeanDefinition();

        // 注册bean定义信息到容器
        BeanDefinitionHolder holder = new BeanDefinitionHolder(beanDefinition, name, new String[]{alias});
        // 使用BeanDefinitionReaderUtils工具类将BeanDefinition注册到容器
        BeanDefinitionReaderUtils.registerBeanDefinition(holder, registry);
    }

    /**
     * 创建扫描器
     */
    protected ClassPathScanningCandidateComponentProvider getScanner() {
        return new ClassPathScanningCandidateComponentProvider(false, environment) {
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                boolean isCandidate = false;
                if (beanDefinition.getMetadata().isIndependent()) {
                    if (!beanDefinition.getMetadata().isAnnotation()) {
                        isCandidate = true;
                    }
                }
                return isCandidate;
            }
        };
    }

    /**
     * 获取base packages
     */
    protected static Set<String> getBasePackages(AnnotationMetadata importingClassMetadata) {
        // 获取到@EnableProcesss注解所有属性
        Map<String, Object> attributes = importingClassMetadata.getAnnotationAttributes(EnableBatchProcess.class.getCanonicalName());
        Set<String> basePackages = new HashSet<>();

        // basePackages 属性是否有配置值，如果有则添加
        for (String pkg : (String[]) attributes.get("scanBasePackages")) {
            if (StringUtils.hasText(pkg)) {
                basePackages.add(pkg);
            }
        }

        // 如果上面两步都没有获取到basePackages，那么这里就默认使用当前项目启动类所在的包为basePackages
        if (basePackages.isEmpty()) {
            basePackages.add(ClassUtils.getPackageName(importingClassMetadata.getClassName()));
        }

        return basePackages;
    }

    /**
     * 获取name
     */
    protected static String getName(Map<String, Object> attributes) {
        String name = (String) attributes.get("jobName");
        if (!StringUtils.hasText(name)) {
            name = (String) attributes.get("value");
        }
        return name;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

}
