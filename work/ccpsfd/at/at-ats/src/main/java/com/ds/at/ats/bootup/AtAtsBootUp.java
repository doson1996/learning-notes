package com.ds.at.ats.bootup;

import com.ds.lib.context.SpringContext;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

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
        ConfigurableApplicationContext applicationContext
                = SpringApplication.run(AtAtsBootUp.class, args);
        // 设置spring上下文
        SpringContext.setApplicationContext(applicationContext);
    }
}
