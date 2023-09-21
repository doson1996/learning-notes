package com.ds.ctp.batch.service.syncisas.executor;

import com.alibaba.fastjson2.JSONObject;
import com.ds.ctp.batch.service.syncisas.operate.Operation;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;

/**
 * @author ds
 * @date 2023/9/19
 * @description 执行器
 */
@Slf4j
public class SimpleExecutor implements Executor {
    @Override
    public boolean execute(Operation operation, JSONObject data, String type) {
        try {
            String methodName = getMethodName(type);
            Method method = operation.getClass().getSuperclass().getDeclaredMethod(methodName, JSONObject.class);
            method.setAccessible(true);
            return (boolean) method.invoke(operation, data);
        } catch (Exception e) {
            log.error("执行器发生异常: ", e);
        }
        return false;
    }

    private String getMethodName(String type) {
        return type.toLowerCase();
    }

}
