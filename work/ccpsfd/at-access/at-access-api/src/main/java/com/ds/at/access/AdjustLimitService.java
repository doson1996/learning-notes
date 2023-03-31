package com.ds.at.access;

import com.ds.lib.annotation.AtBusiness;

import java.util.Map;

/**
 * @author ds
 * @date 2023/3/31
 * @description
 */

public class AdjustLimitService implements AtAdjustLimit {
    @AtBusiness(value = "001")
    @Override
    public Map<String, Object> apply(Map<String, Object> input) {
        return null;
    }

}
