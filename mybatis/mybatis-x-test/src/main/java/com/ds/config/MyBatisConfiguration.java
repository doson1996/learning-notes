package com.ds.config;

import com.ds.mybatisx.spring.annotion.MapperxScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ds
 * @date 2023/11/21
 * @description
 */
@Configuration
@MapperxScan(basePackages = "com.ds.mapper")
@ComponentScan("com.ds")
public class MyBatisConfiguration {

}
