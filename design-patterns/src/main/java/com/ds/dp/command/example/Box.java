package com.ds.dp.command.example;

/**
 * @Author ds
 * @Date 2021/3/19 11:41
 * @Description
 */
public class Box {

    private Command command;

    public Box(Command command) {
        this.command = command;
    }

    public void powerButton() {
        command.execute();
    }
}
