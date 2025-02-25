package com.ds.dp.state.simple;

/**
 * @Author ds
 * @Date 2021/4/1 10:24
 * @Description 状态模式 （本质：根据状态来分离和选择行为）
 * 优点: 1.简化逻辑控制
 * 2.更好的分离状态和行为
 * 3.更好的扩展性
 * 4.显示化进行状态转换
 * 缺点： 一个状态对应一个状态处理类，会使得程序引入太多的状态类，变得杂乱
 */
public class Client {

    public static void main(String[] args) {

        State state = new ConcreteStateA();

        Context context = new Context();
        context.setState(state);
        context.request("参数");

    }
}
