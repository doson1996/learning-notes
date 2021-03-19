package com.ds.dp.command.example1;

/**
 * @Author ds
 * @Date 2021/3/19 14:14
 * @Description
 */
public class Invoker {

    private Command command;

    public Invoker(Command command) {
        this.command = command;
    }

    public void startPrint(){
        command.execute();
    }

}
