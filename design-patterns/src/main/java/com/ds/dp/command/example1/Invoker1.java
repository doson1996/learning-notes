package com.ds.dp.command.example1;

/**
 * @Author ds
 * @Date 2021/3/19 14:14
 * @Description
 */
public class Invoker1 {

    public void startPrint(Command command) {
        System.out.println("Invoker1 开始打印");
        command.execute();
        System.out.println("Invoker1 打印结束");
    }

}
