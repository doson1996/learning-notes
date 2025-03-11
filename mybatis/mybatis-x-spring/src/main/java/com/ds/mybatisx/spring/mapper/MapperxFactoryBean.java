package com.ds.mybatisx.spring.mapper;

/**
 * @author ds
 * @date 2023/11/21
 * @description
 */

import com.ds.mybatisx.session.SqlSessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MapperxFactoryBean<T> implements FactoryBean<T>, ApplicationContextAware {

    private Class<T> mapperInterface;

    private ApplicationContext applicationContext;

    public MapperxFactoryBean() {

    }

    public void setMapperInterface(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public T getObject() throws Exception {
        return applicationContext.getBean(SqlSessionFactory.class).openSession().getMapper(mapperInterface);
    }

    @Override
    public Class<?> getObjectType() {
        return this.mapperInterface;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
