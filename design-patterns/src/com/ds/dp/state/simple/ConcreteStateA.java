package com.ds.dp.state.simple;

/**
 * @Author ds
 * @Date 2021/4/1 10:20
 * @Description 实现一个与content的一个特定状态相关的行为
 */
public class ConcreteStateA implements State{

    @Override
    public void handle(String parameter) {
        //实现具体的处理
        System.out.println("A parameter = " + parameter);
    }
}
