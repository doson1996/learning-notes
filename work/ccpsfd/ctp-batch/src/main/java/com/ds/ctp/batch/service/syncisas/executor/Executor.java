package com.ds.ctp.batch.service.syncisas.executor;

import com.alibaba.fastjson2.JSONObject;
import com.ds.ctp.batch.service.syncisas.operate.Operation;

/**
 * @author ds
 * @date 2023/9/19
 * @description
 */
public interface Executor {

    boolean execute(Operation operation, JSONObject data, String type);

}
