package com.ds.springframework.chapte01.enable;

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
@Import(ARegistrar.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableA {

    /**
     * 路径
     * @return
     */
    String path() default "";

}
