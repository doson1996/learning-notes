package com.ds.springframework.chapte01.handler;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @author ds
 * @date 2023/12/27
 * @description
 */
public class DsNamespaceHandler extends NamespaceHandlerSupport {
    
    @Override
    public void init() {
        this.registerBeanDefinitionParser("ds", new DsBeanDefinitionParser());
    }

}
