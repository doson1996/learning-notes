package com.ds.dp.responsibility.icbc;

import java.util.Map;
import java.util.Objects;

/**
 * 参数校验
 *
 * @author ds
 */
public class DataCheckStep extends ExceptionStep {

    @Override
    public void execute(Map<String, Object> context) throws Exception {
        process(context);
        if ("200".equals(context.get("code"))) {
            // 参数校验成功执行步骤
            context.put("msg","参数校验成功");
            setNextStep(new ResultStep());
        } else {
            setNextStep(new ResultStep());
        }

    }

    /**
     * @param context
     * @return
     */
    private Map<String, Object> process(Map<String, Object> context) {
        context.put("code", "200");

        String applyNo = getStrValue(context, "applyNo");
        if (applyNo.isEmpty()) {
            context.put("code", "400");
            context.put("msg", "申请编号不能为空!");
            return context;
        }
        if (applyNo.length() > 17) {
            context.put("code", "400");
            context.put("msg", "申请编号长度不能大于17!");
            return context;
        }

        return context;
    }

    private String getStrValue(Map<String, Object> context, String key) {
        Object value = context.get(key);
        if (Objects.isNull(value)) {
            return "";
        }

        return String.valueOf(value);
    }
}
