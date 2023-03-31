package com.ds.lib.annotation;

import java.lang.annotation.*;

/**
 * @author ds
 * @date 2023/3/31
 * @description
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AtBusiness {

    String value() default "";

    String trxCode() default "";

}
