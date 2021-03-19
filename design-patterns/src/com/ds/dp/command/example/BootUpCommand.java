package com.ds.dp.command.example;

/**
 * @Author ds
 * @Date 2021/3/19 11:38
 * @Description 开机命令
 */
public class BootUpCommand implements Command{

    private MainBoard mainBoard;

    public BootUpCommand(MainBoard mainBoard) {
        this.mainBoard = mainBoard;
    }

    @Override
    public void execute() {
        //对于命令对象（开机按钮），完全不知道如何开机，会转调主板对象，让主板去完成开机的功能
        mainBoard.bootUp();
    }

}
