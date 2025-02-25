package com.ds.dp.command.example;

/**
 * @Author ds
 * @Date 2021/3/19 11:33
 * @Description 技嘉主板
 */
public class GigaMainBoard implements MainBoard {

    @Override
    public void bootUp() {
        System.out.println("技嘉主板正在开机...");
        System.out.println("技嘉主板开机中...");
        System.out.println("技嘉主板已开机...");
    }
}
