package com.ds.dp.responsibility.icbc;

import java.util.HashMap;
import java.util.Map;

/**
 * 某业务Service实现类
 * @author ds
 */
public class XxServiceImpl {

    public static void main(String[] args) {
        Map<String,Object> context = new HashMap<>();
        context.put("applyNo", "1");

        StartStep startStep = new StartStep();
        startStep.handler(context);
    }

}
