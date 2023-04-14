package com.ds.lib.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author ds
 * @date 2023/4/13
 * @description
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ProcessV1 {

    @AliasFor(annotation = Component.class)
    String value() default "";

    /**
     * 批量名
     */
    String jobName()  default "";

}
