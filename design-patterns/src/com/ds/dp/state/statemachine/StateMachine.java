package com.ds.dp.state.statemachine;

/**
 * @Author ds
 * @Date 2021/4/1 11:45
 * @Description 状态机，相当于状态模式的context
 */
public class StateMachine {

    /**
     * 持有一个状态对象
     */
    private State state;

    /**
     * 包含流程处理所需的业务数据对象
     */
    private Object businessVo;

    /**
     * 执行工作，客户端处理流程的接口方法
     * 在客户完成自己业务后调用
     */
    public void doWord(){
        //转调相应的状态对象真正完成功能处理
        this.state.doWork(this);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Object getBusinessVo() {
        return businessVo;
    }

    public void setBusinessVo(Object businessVo) {
        this.businessVo = businessVo;
    }
}
