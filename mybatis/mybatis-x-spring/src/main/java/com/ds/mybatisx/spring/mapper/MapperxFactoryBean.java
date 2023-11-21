package com.ds.mybatisx.spring.mapper;

import com.ds.mybatisx.session.SqlSession;
import com.ds.mybatisx.starter.MyBatsixStarter;
import org.springframework.beans.factory.FactoryBean;

public class MapperxFactoryBean<T> implements FactoryBean<T> {

    private Class<T> mapperInterface;

    public MapperxFactoryBean() {
        // intentionally empty
    }

//    public MapperxFactoryBean(Class<T> mapperInterface) {
//        this.mapperInterface = mapperInterface;
//    }

    public void setMapperInterface(Class<T> mapperInterface) {
        this.mapperInterface = mapperInterface;
    }

    @Override
    public T getObject() throws Exception {
        System.out.println("mapperInterface = " + mapperInterface);
        return MyBatsixStarter.getMyBatsixStarter().getMapper(mapperInterface, "mybatisx.xml");
    }

    @Override
    public Class<?> getObjectType() {
        return this.mapperInterface;
    }

}
