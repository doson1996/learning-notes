package com.ds.dp.factory.abstractF;

/**
 * @Author ds
 * @Date 2021/3/12 9:30
 * @Description 技嘉主板
 */
public class GaMainBoard implements MainBoardApi{

    private int cpuHoles = 0;

    public GaMainBoard(int cpuHoles){
      this.cpuHoles = cpuHoles;
    }

    @Override
    public void installCpu() {
        System.out.println("GaMainBoard " + cpuHoles);
    }
}
