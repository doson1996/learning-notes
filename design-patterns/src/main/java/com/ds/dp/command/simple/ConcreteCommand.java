package com.ds.dp.command.simple;

/**
 * @Author ds
 * @Date 2021/3/18 9:25
 * @Description 具体的命令实现对象
 */
public class ConcreteCommand implements Command {

    /**
     * 命令接收者
     */
    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    /**
     * 示意，命令对象可以有自己的对象
     */
    private String state;

    @Override
    public void execute() {
        //通常会调用接收者，让接收者来真正执行功能
        receiver.action();
    }
}
