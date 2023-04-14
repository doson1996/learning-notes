package com.ds.lib.annotation;

import com.ds.lib.scan.ProcessScannerRegistrar;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ds
 * @date 2023/4/13
 * @description
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(ProcessScannerRegistrar.class)
public @interface EnableBatchProcess {

    String value() default "";

    String[] scanBasePackages() default {};

}
