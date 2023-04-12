package com.ds.lib.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.applet.AppletContext;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ds
 * @date 2023/4/12
 * @description
 */
public class SpringContext {

    private static final Map<String, Object> providerMap = new ConcurrentHashMap<>(16);

    public static void putService(String name, Object service) {
        providerMap.put(name, service);
    }

    public static Object getService(String name) {
        return providerMap.get(name);
    }

    private static ApplicationContext context;


    public static void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    public static ApplicationContext getContext() {
        return context;
    }

}
