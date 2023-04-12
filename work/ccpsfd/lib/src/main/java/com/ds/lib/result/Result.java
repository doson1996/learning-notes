package com.ds.lib.result;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ds
 * @date 2023/4/12
 * @description
 */
public class Result {

    public static Map<String, Object> success() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", "0");
        result.put("message", "success");
        return result;
    }

    public static Map<String, Object> fail() {
        Map<String, Object> result = new HashMap<>();
        result.put("code", "1");
        result.put("message", "fail");
        return result;
    }

}
