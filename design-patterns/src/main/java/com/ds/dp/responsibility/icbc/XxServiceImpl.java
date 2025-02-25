package com.ds.dp.responsibility.icbc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 某业务Service实现类
 *
 * @author ds
 */
public class XxServiceImpl {

    public static void main(String[] args) {
        Map<String, Object> context = new HashMap<>();
        context.put("applyNo", new ArrayList<>());

        StartStep startStep = new StartStep();
        startStep.handler(context);
        System.out.println("result = " + context);
    }

}
