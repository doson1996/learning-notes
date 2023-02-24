package com.ds.dp.command.example1;

/**
 * @Author ds
 * @Date 2021/3/19 14:23
 * @Description 退化的命令模式实例java回调
 */
public class Test1 {

    public static void main(String[] args) {

        Command1 command1 = new Command1() {

            private String str;

            @Override
            public void execute() {
                System.out.println(str);
            }

            @Override
            public void setStr(String str) {
                this.str = str;
            }
        };

        command1.setStr("退化的命令模式实例java回调");

        Invoker2 invoker2 = new Invoker2();
        invoker2.startPrint(command1);
    }
}
