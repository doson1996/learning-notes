package com.ds.dp.state.statemachine;

/**
 * @Author ds
 * @Date 2021/4/1 11:46
 * @Description 公共状态的接口
 */
public interface State {

    /**
     * 执行状态对应的功能处理
     *
     * @param context
     */
    void doWork(StateMachine context);
}
