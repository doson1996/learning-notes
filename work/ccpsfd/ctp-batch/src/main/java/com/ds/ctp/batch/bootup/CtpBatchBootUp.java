package com.ds.ctp.batch.bootup;

import com.ds.lib.annotation.EnableBatchProcess;
import com.ds.lib.batch.BatchBootUp;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author ds
 * @date 2023/4/13
 * @description
 */
@EnableDubbo
@EnableBatchProcess(scanBasePackages = {"com.ds.ctp.batch"})
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class,
        scanBasePackages = {"com.ds.ctp.batch", "com.ds.lib"}
)
public class CtpBatchBootUp {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CtpBatchBootUp.class, args);
        new BatchBootUp().bootUp(context, args);
    }
}
