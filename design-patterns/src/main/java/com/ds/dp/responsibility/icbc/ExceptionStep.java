package com.ds.dp.responsibility.icbc;

import java.util.Map;

/**
 * 发生异常的步骤继承此类
 *
 * @author ds
 */
public abstract class ExceptionStep extends AbstractStep {

    @Override
    public void handler(Map<String, Object> context) {
        try {
            execute(context);
            if (getNextStep() == null) {
                return;
            }
            getNextStep().handler(context);
        } catch (Exception e) {
            // 异常处理业务逻辑
            context.put("code", "500");
            context.put("msg", this.getClass().getSimpleName() + " step exception");
        }
    }
}
