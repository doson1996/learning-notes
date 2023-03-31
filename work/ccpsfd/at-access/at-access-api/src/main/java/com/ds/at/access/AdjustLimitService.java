package com.ds.at.access;

import com.ds.lib.annotation.AtBusiness;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.Map;

/**
 * @author ds
 * @date 2023/3/31
 * @description
 */
@DubboService(version = "1.0")
public class AdjustLimitService implements AdjustLimit {
    @AtBusiness(value = "001")
    @Override
    public Map<String, Object> apply(Map<String, Object> input) {
        return null;
    }

}
