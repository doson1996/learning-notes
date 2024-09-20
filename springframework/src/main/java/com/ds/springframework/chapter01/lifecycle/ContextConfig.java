package com.ds.springframework.chapter01.lifecycle;

import com.ds.springframework.chapter01.aliasfor.DomainScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author ds
 * @date 2024/2/8
 * @description
 */
@Configuration
//@ComponentScan("com.ds.springframework.chapter01.lifecycle")
@DomainScan(scanBasePackages = "com.ds.springframework.chapter01.lifecycle")
public class ContextConfig {

}
