package com.ds.lib.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.applet.AppletContext;

/**
 * @author ds
 * @date 2023/4/12
 * @description
 */
public class SpringContext {

    private static ApplicationContext context;


    public static void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }

}
