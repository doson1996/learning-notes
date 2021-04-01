package com.ds.dp.state.statemachine;

/**
 * @Author ds
 * @Date 2021/4/1 13:39
 * @Description
 */
public class ProjectManagerState implements LeaveRequestState{

    @Override
    public void doWork(StateMachine context) {

        LeaveRequestModel lrm = (LeaveRequestModel) context.getBusinessVo();

        if ("同意".equals(lrm.getResult())){

            if (lrm.getLeaveDays() > 3){

                // 大于3天项目经理同意后，提交给部门经理
                context.setState(new DepManagerState());
            } else {

                // 三天以内的假期，就不用提交给部门经理，转向审核结束状态
                context.setState(new AuditOverState());
            }

        } else {
            // 如果项目经理不同意，就不用提交给部门经理，转向审核结束状态
            context.setState(new AuditOverState());
        }

    }
}
