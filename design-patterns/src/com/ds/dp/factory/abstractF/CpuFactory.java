package com.ds.dp.factory.abstractF;

/**
 * @Author ds
 * @Date 2021/3/12 9:33
 * @Description cpu简单工厂
 */
public class CpuFactory {

    /**
     * 创建cpu
     * @param type
     * @return
     */
    public static CpuApi createCpu(int type){
        CpuApi cpu = null;

        if(type == 1){
            cpu = new IntelCpu(10);
        }

        if (type == 2){
            cpu = new AmdCpu(20);
        }

        return cpu;
    }
}
