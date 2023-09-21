package com.ds.ctp.batch.service.syncisas.factory;

import com.ds.ctp.batch.service.syncisas.operate.BaseOperation;
import com.ds.ctp.batch.service.syncisas.operate.CtpUserOperation;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ds
 * @date 2023/9/21
 * @description
 */
public class OperationFactory {

    private static final Map<String, BaseOperation> OPERATION_MAP = new HashMap<>();

    static {
        OPERATION_MAP.put("CTP_USER", new CtpUserOperation());
    }

    public static BaseOperation createOperation(String table) {
        return OPERATION_MAP.get(table);
    }

}
