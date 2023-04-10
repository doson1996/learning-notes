package com.ds.at.access.dubbo;

import com.ds.at.ats.ATSAdjustLimit;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.context.annotation.Configuration;

/**
 * @author ds
 * @date 2023/4/10
 * @description
 */
@Configuration
public class ConsumerConfig {

    @DubboReference(version = "1.0")
    ATSAdjustLimit atsAdjustLimit;

}
