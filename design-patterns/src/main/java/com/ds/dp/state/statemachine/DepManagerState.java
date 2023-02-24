package com.ds.dp.state.statemachine;

/**
 * @Author ds
 * @Date 2021/4/1 13:39
 * @Description
 */
public class DepManagerState implements LeaveRequestState{

    @Override
    public void doWork(StateMachine context) {

        LeaveRequestModel lrm = (LeaveRequestModel) context.getBusinessVo();

        context.setState(new AuditOverState());

    }
}
