package com.ds.at.ats.adjust;

import com.ds.at.ats.ATSAdjustLimit;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Map;

/**
 * @author ds
 * @date 2023/4/10
 * @description
 */
@Slf4j
@DubboService(version = "1.0")
public class ATSAdjustLimitService implements ATSAdjustLimit {
    @Override
    public Map<String, Object> apply(Map<String, Object> input) {
        log.info("apply...");
        return input;
    }
}
