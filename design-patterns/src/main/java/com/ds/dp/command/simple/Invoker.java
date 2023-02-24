package com.ds.dp.command.simple;

/**
 * @Author ds
 * @Date 2021/3/19 11:24
 * @Description 调用者
 */
public class Invoker {

    private Command command;

    public void setCommand(Command command){
        this.command = command;
    }

    /**
     * 示意方法，要求命令执行请求
     */
    public void runCommand(){
        command.execute();
    }
}
