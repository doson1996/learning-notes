package com.ds.dp.state.statemachine;

/**
 * @Author ds
 * @Date 2021/4/1 13:34
 * @Description
 */
public class LeaveRequestModel {

    private String user;

    private String beginDate;

    /**
     * 请假天数
     */
    private int leaveDays;

    /**
     * 审核结果
     */
    private String result;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public int getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(int leaveDays) {
        this.leaveDays = leaveDays;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
