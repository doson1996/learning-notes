package com.ds.dp.command.example1;

/**
 * @Author ds
 * @Date 2021/3/19 14:15
 * @Description 退化的命令模式
 */
public class Test {

    public static void main(String[] args) {

        Command command = new Print("退化的命令模式");
        Invoker invoker = new Invoker(command);
        invoker.startPrint();

        Invoker1 invoker1 = new Invoker1();
        invoker1.startPrint(command);
    }
}
