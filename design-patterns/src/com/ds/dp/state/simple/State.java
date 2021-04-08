package com.ds.dp.state.simple;

/**
 * @Author ds
 * @Date 2021/4/1 10:18
 * @Description 封装与content的一个特定状态相关的行为
 */
public interface State {

    /**
     * 状态对应的处理
     * @param parameter
     */
    void handle(String parameter);
}
