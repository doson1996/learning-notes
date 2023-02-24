package com.ds.dp.command.simple;

/**
 * @Author ds
 * @Date 2021/3/18 9:32
 * @Description 命令模式    （本质：封装请求）
 *                  优点：
 *                      1.更松散的耦合
 *                      2.更动态的控制
 *                      3.很自然的复合命令
 *                      4.更好的扩展性
 */
public class CommandTest {

    public static void main(String[] args) {
        assemble();
    }

    public static void assemble(){

        Receiver receiver = new Receiver();
        Command command = new ConcreteCommand(receiver);
        Invoker invoker = new Invoker();
        invoker.setCommand(command);
        invoker.runCommand();
    }
}
