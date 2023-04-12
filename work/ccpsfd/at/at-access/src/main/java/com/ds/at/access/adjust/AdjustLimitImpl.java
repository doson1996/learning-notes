package com.ds.at.access.adjust;

import com.ds.at.access.AdjustLimit;
import com.ds.lib.annotation.AtBusiness;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author ds
 * @date 2023/3/31
 * @description
 */
@Slf4j
@Service
public class AdjustLimitImpl implements AdjustLimit {

    @AtBusiness(trxCode = "001")
    @Override
    public Map<String, Object> apply(Map<String, Object> input) {

        return null;
    }

}
