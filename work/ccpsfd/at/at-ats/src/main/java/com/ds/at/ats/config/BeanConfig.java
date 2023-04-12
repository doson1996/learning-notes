package com.ds.at.ats.config;

import com.ds.at.ats.AdjustLimitService;
import com.ds.lib.proxy.Invoker;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ds
 * @date 2023/4/12
 * @description
 */
@Configuration
public class BeanConfig {

    @DubboService(version = "1.0")
    @Bean("adjustLimitService")
    public AdjustLimitService adjustLimitService() {
        return (AdjustLimitService) new Invoker(AdjustLimitService.class).getInstance();
    }

}
