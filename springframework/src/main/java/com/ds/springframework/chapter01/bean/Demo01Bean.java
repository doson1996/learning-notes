package com.ds.springframework.chapter01.bean;

/**
 * @see org.springframework.beans.factory.BeanFactory
 * bean生命周期
 * @author ds
 */
public class Demo01Bean {
    public static void main(String[] args) {
        /**
         * 1.实例化 (在堆中申请内存，属性为默认值)
         *    {@link org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory createBeanInstance}
         *
         * 2.初始化
         *      设置属性
         *          设置自定义属性   {@link org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory populateBean}
         *          设置容器对象属性 {@link org.springframework.context.support.ApplicationContextAwareProcessor invokeAwareInterfaces}
         *      初始化之前 {@link org.springframework.beans.factory.config.BeanPostProcessor postProcessBeforeInitialization}
         *      初始化之后 {@link org.springframework.beans.factory.config.BeanPostProcessor postProcessAfterInitialization}
         * 3.使用
         *
         * 4.销毁
         * @see org.springframework.context.support.AbstractApplicationContext close
         */
    }
}
