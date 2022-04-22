package com.ds.springframework.chapte01;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.support.AbstractApplicationContext;

/**
 * @see AbstractApplicationContext refresh
 * @author ds
 */
public class Demo01 {
    public static void main(String[] args) {
        new AbstractApplicationContext() {
            @Override
            protected void refreshBeanFactory() throws BeansException, IllegalStateException {

            }

            @Override
            protected void closeBeanFactory() {

            }

            @Override
            public ConfigurableListableBeanFactory getBeanFactory() throws IllegalStateException {
                return null;
            }
        }.refresh();


    }
}
