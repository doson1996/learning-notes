package com.ds.at.access.bootup;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author ds
 * @date 2023/3/31
 * @description
 */
@EnableDubbo
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,
        scanBasePackages = {"com.ds.at.access"}
)
public class AtAccessBootUp {
    public static void main(String[] args) {
        SpringApplication.run(AtAccessBootUp.class, args);
    }
}
