package com.ds.dp.memento.unused;

/**
 * @Author ds
 * @Date 2021/4/2 9:44
 * @Description 不使用设计模式
 */
public class Client {

    public static void main(String[] args) {

        FlowAMock flowAMock = new FlowAMock("test");
        flowAMock.runPhaseOne();
        int tempResult = flowAMock.getTempResult();
        String tempState = flowAMock.getTempState();

        flowAMock.schema1();


        flowAMock.setTempResult(tempResult);
        flowAMock.setTempState(tempState);
        flowAMock.schema2();
    }
}
