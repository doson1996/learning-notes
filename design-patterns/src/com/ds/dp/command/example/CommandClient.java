package com.ds.dp.command.example;

/**
 * @Author ds
 * @Date 2021/3/19 11:43
 * @Description
 */
public class CommandClient {

    public static void main(String[] args) {

        MainBoard mainBoard = new GigaMainBoard();

        Command command = new BootUpCommand(mainBoard);

        Box box = new Box(command);

        box.powerButton();

    }
}
