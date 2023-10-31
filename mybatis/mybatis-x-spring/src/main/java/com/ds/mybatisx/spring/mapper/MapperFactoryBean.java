package com.ds.mybatisx.spring.mapper;

import com.ds.mybatisx.io.Resources;
import com.ds.mybatisx.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.FactoryBean;

public class MapperFactoryBean<T> implements FactoryBean<T> {

    private Class<T> mapperInterface;

    @Override
    public T getObject() throws Exception {
        return new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsStream("mybatisx.xml"))
                .openSession()
                .getMapper(mapperInterface);
    }

    @Override
    public Class<?> getObjectType() {
        return this.mapperInterface;
    }

}
