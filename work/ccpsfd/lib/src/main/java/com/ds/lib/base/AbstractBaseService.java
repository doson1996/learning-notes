package com.ds.lib.base;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ds
 * @date 2023/4/12
 * @description
 */
@Slf4j
public abstract class AbstractBaseService {

    public Map<String, Object> doTrans(Map<String, Object> input) {
        Map<String, Object> output = new HashMap<>();
        try {
            output = execute(input);
        } catch (Exception e) {
            output.put("code", "-1");
            log.error("业务处理发生异常..., ex: ", e);
        }
        return output;
    }

    protected abstract Map<String, Object> execute(Map<String, Object> input);

}
