package com.ds.springframework.ciz.beans.factory.config;

import java.lang.reflect.Constructor;

import com.ds.springframework.ciz.beans.PropertyValues;

/**
 * @author ds
 * @date 2024/12/5
 * @description
 */
public class BeanDefinition {

    private Class beanClass;

    private PropertyValues propertyValues;

    private Constructor ctor;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues == null ? new PropertyValues() : propertyValues;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }


    public Constructor getCtor() {
        return ctor;
    }

    public void setCtor(Constructor ctor) {
        this.ctor = ctor;
    }

}
