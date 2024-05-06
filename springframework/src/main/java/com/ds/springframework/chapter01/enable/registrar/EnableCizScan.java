package com.ds.springframework.chapter01.enable.registrar;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ds
 * @date 2024/4/12
 * @description
 */
@Import(CizScannerRegistrar.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableCizScan {

    /**
     * 路径
     * @return
     */
    String path() default "";

}
