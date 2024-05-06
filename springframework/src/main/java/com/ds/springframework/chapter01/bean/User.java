package com.ds.springframework.chapter01.bean;

import lombok.Data;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author ds
 */
@Data
public class User implements FactoryBean<User> {

    private Integer id;

    private String name;

    @Override
    public User getObject() throws Exception {
        User user = new User();
        user.setId(1);
        user.setName("张三");
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
