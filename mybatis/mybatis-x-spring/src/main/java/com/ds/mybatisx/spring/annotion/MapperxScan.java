package com.ds.mybatisx.spring.annotion;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ds
 */
@Import(MapperxScannerRegistrar.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MapperxScan {

    String[] value() default {};

    String[] basePackages() default {};

}
