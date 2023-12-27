package com.ds.springframework.chapte01.handler;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
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
    @Override
    protected Class<?> getBeanClass(Element element) {
        Class<?> clazz;
        try {
            clazz = Class.forName(element.getAttribute("class"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return clazz;
    }

    /**
     * 偷梁换柱
     * @param element
     * @return
     */
//    @Override
//    protected Class<?> getBeanClass(Element element) {
//       return FactoryBean.class;
//    }

    @Override
    protected void doParse(Element element, BeanDefinitionBuilder builder) {
        AbstractBeanDefinition beanDefinition = builder.getBeanDefinition();
        //beanDefinition.setBeanClass(this.getBeanClass(element));
    }

    private static String resolveAttribute(Element element, String attributeName) {
        String attributeValue = element.getAttribute(attributeName);
        return attributeValue;
    }
}
