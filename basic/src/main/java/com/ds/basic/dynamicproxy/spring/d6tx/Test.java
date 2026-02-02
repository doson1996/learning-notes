package com.ds.basic.dynamicproxy.spring.d6tx;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author ds
 * @date 2026/1/5
 * @description 事务
 */
public class Test {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TxConfig.class);
        TxService txService = context.getBean(TxService.class);
        txService.tx();
    }
}
