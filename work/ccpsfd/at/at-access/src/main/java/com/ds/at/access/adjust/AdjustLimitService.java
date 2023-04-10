package com.ds.at.access.adjust;

import com.ds.at.access.AdjustLimit;
import com.ds.at.ats.ATSAdjustLimit;
import com.ds.lib.annotation.AtBusiness;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author ds
 * @date 2023/3/31
 * @description
 */
@Slf4j
@Service
public class AdjustLimitService implements AdjustLimit {

    @DubboReference(version = "1.0")
    ATSAdjustLimit atsAdjustLimit;

    @AtBusiness(value = "001")
    @Override
    public Map<String, Object> apply(Map<String, Object> input) {
        Map<String, Object> apply = atsAdjustLimit.apply(input);
        return apply;
    }

}
