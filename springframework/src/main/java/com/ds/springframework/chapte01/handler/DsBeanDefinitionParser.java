package com.ds.springframework.chapte01.handler;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.util.ClassUtils;
import org.w3c.dom.Element;

/**
 * @author ds
 * @date 2023/12/27
 * @description
 */
public class DsBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

    /**
     * 正常使用class
     * @param element
     * @return
     */
//    @Override
//    protected Class<?> getBeanClass(Element element) {
//        Class<?> clazz;
//        try {
//            clazz = Class.forName(element.getAttribute("class"));
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        return clazz;
//    }

    /**
     * 偷梁换柱
     *
     * @param element
     * @return
     */
    @Override
    protected Class<?> getBeanClass(Element element) {
        return DsFactoryBean.class;
    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        String clazz = resolveAttribute(element, "class");
        Class<?> className;
        try {
            className = ClassUtils.forName(clazz, this.getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        builder.addPropertyValue("className", className.getName());
    }

    private static String resolveAttribute(Element element, String attributeName) {
        return element.getAttribute(attributeName);
    }
    
}
