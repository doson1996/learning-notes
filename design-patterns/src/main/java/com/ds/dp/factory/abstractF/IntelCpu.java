package com.ds.dp.factory.abstractF;

/**
 * @Author ds
 * @Date 2021/3/12 9:25
 * @Description
 */
public class IntelCpu implements CpuApi{

    /**
     * 脚针数
     */
    private int pins = 0;

    public IntelCpu(int pins){
        this.pins = pins;
    }

    @Override
    public void calculate() {
        System.out.println("IntelCPU " + pins);
    }
}
