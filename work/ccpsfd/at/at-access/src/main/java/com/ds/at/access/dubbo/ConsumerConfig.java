package com.ds.at.access.dubbo;

import com.ds.at.ats.AdjustLimitService;
import com.ds.lib.context.SpringContext;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @author ds
 * @date 2023/4/10
 * @description
 */
@Configuration
public class ConsumerConfig {

    @DubboReference(version = "1.0")
    AdjustLimitService adjustLimitService;

    /**
     * 模拟dubbo SpringContainer
     */
    @PostConstruct
    public void init() {
        SpringContext.putService("adjustLimitService", adjustLimitService);
    }

}
