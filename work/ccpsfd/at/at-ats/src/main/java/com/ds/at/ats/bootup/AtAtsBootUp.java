package com.ds.at.ats.bootup;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author ds
 * @date 2023/4/10
 * @description
 */
@EnableDubbo
@DubboComponentScan(basePackages = {"com.ds.at.ats"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,
        scanBasePackages = {"com.ds.at.ats"}
)
public class AtAtsBootUp {
    public static void main(String[] args) {
        SpringApplication.run(AtAtsBootUp.class, args);
    }
}
