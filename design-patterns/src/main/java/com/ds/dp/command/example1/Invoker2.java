package com.ds.dp.command.example1;

/**
 * @Author ds
 * @Date 2021/3/19 14:14
 * @Description
 */
public class Invoker2 {

    public void startPrint(Command1 command1){
        System.out.println("Invoker2 开始打印");
        command1.execute();
        System.out.println("Invoker2 打印结束");
    }

}
