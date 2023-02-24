package com.ds.dp.responsibility.icbc;

import java.util.Map;

/**
 * 责任链模式
 * 所有步骤顶级父类
 * @author ds
 */
public abstract class AbstractStep {

    private AbstractStep nextStep;

    /**
     * 真正的业务逻辑处理方法
     *
     * @param context
     * @throws Exception
     */
    public abstract void execute(Map<String, Object> context) throws Exception;

    /**
     * 当前步骤处理方法
     *
     * @param context
     */
    public abstract void handler(Map<String, Object> context);

    public AbstractStep getNextStep() {
        return nextStep;
    }

    public void setNextStep(AbstractStep nextStep) {
        this.nextStep = nextStep;
    }
}
