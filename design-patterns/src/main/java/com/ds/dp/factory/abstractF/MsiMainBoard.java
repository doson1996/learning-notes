package com.ds.dp.factory.abstractF;

/**
 * @Author ds
 * @Date 2021/3/12 9:30
 * @Description 微星主板
 */
public class MsiMainBoard implements MainBoardApi {

    private int cpuHoles = 0;

    public MsiMainBoard(int cpuHoles) {
        this.cpuHoles = cpuHoles;
    }

    @Override
    public void installCpu() {
        System.out.println("MsiMainBoard " + cpuHoles);
    }
}
