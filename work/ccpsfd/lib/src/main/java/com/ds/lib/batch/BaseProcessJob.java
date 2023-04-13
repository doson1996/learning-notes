package com.ds.lib.batch;

/**
 * @author ds
 * @date 2023/4/13
 * @description
 */
public abstract class BaseProcessJob {

    public ProcessResult run() {
        // todo 扩展，平台监控、执行控制
        return mainProcess();
    }

    /**
     * 逻辑处理方法
     * @return
     */
    protected abstract ProcessResult mainProcess();

}
