package com.ds.dp.command.example1;

/**
 * @Author ds
 * @Date 2021/3/19 14:12
 * @Description
 */
public class Print implements Command{

    private String str = "";

    public Print(String str) {
        this.str = str;
    }

    @Override
    public void execute() {
        System.out.println("打印----" + str);
    }
}
