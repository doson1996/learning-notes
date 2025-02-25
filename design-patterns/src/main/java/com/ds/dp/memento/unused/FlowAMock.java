package com.ds.dp.memento.unused;

/**
 * @Author ds
 * @Date 2021/4/2 9:45
 * @Description
 */
public class FlowAMock {

    /**
     * 流程名称
     */
    private String flowName;

    private int tempResult;

    private String tempState;

    /**
     * 运行流程的第一个阶段
     */
    public void runPhaseOne() {

        tempResult = 1;
        tempState = "runPhaseOne";
    }


    public void schema1() {

        tempState = tempState + ",schema1";
        System.out.println(this.tempState + "--" + this.tempResult);
        this.tempState += 1;
    }

    public void schema2() {

        tempState = tempState + ",schema2";
        System.out.println(this.tempState + "--" + this.tempResult);
        this.tempState += 2;
    }

    public FlowAMock(String flowName) {
        this.flowName = flowName;
    }

    public int getTempResult() {
        return tempResult;
    }

    public void setTempResult(int tempResult) {
        this.tempResult = tempResult;
    }

    public String getTempState() {
        return tempState;
    }

    public void setTempState(String tempState) {
        this.tempState = tempState;
    }


}
