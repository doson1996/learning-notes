package com.ds.springframework.chapter01.handler;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.ClassUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ds
 * @date 2023/12/27
 * @description
 */
public class DsFactoryBean implements FactoryBean {

    private String className;

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public Object getObject() throws Exception {
        Map<Mode, Object> result = new HashMap<>();
        Class<?> clazz = ClassUtils.forName(className, this.getClass().getClassLoader());
        // 同步执行对象
        Object sync = clazz.newInstance();
        // 异步执行对象
        Object async = ProxyFactory.getInstance().create(sync);
        result.put(Mode.SYNC, sync);
        result.put(Mode.ASYNC, async);
        return result;
    }

    @Override
    public Class<?> getObjectType() {
        return Map.class;
    }
}
