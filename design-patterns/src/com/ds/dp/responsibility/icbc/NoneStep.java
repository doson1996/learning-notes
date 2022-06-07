package com.ds.dp.responsibility.icbc;

import java.util.Map;

/**
 * 不会发生异常的步骤继承此类
 *
 * @author ds
 */
public abstract class NoneStep extends AbstractStep {

    @Override
    public void handler(Map<String, Object> context) {
        try {
            execute(context);
            if (getNextStep() == null) {
                return;
            }
            getNextStep().handler(context);
        } catch (Exception e) {
            // 不做处理
        }
    }
}
