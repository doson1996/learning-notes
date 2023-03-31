package com.ds.at.access;

import java.util.Map;

/**
 * @author ds
 * @date 2023/3/31
 * @description
 */
public interface AdjustLimit {

    /**
     * 申请
     * @param input
     * @return
     */
    Map<String, Object> apply(Map<String, Object> input);

}
