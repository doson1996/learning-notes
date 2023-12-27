package com.ds.springframework.chapte01.handler;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author ds
 * @date 2023/12/27
 * @description
 */
public class DsFactoryBean<T> implements FactoryBean<T> {
    @Override
    public T getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }
}
