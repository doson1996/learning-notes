package com.ds.dp.state.statemachine;

/**
 * @Author ds
 * @Date 2021/4/1 13:45
 * @Description 处理审核结束
 */
public class AuditOverState implements LeaveRequestState {

    @Override
    public void doWork(StateMachine context) {

        LeaveRequestModel lrm = (LeaveRequestModel) context.getBusinessVo();

    }
}
