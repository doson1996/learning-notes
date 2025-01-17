package com.ds.springframework.ciz.beans.factory.support;

import com.ds.springframework.ciz.beans.BeansException;
import com.ds.springframework.ciz.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * @author ds
 * @date 2025/1/16
 * @description Cglib 实例化
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        try {
            Class beanClass = beanDefinition.getBeanClass();
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(beanClass);
            enhancer.setCallback(new NoOp() {
                @Override
                public int hashCode() {
                    return super.hashCode();
                }
            });
            if (ctor == null) {
                return enhancer.create();
            } else {
                return enhancer.create(ctor.getParameterTypes(), args);
            }
        } catch (Exception e) {
            throw new BeansException("cglib instantiate error", e);
        }
    }

}
