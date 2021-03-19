package com.ds.dp.command.example;

/**
 * @Author ds
 * @Date 2021/3/19 11:37
 * @Description 微星主板
 */
public class MsiMainBoard implements MainBoard{

    @Override
    public void bootUp() {
        System.out.println("微星主板正在开机...");
        System.out.println("微星主板开机中...");
        System.out.println("微星主板已开机...");
    }
}
