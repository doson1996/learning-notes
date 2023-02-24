package com.ds.dp.factory.abstractF;

/**
 * @Author ds
 * @Date 2021/3/12 9:42
 * @Description 装机工程师
 */
public class Computer {

    CpuApi cpuApi = null;

    MainBoardApi mainBoardApi = null;

    /**
     * 组装电脑
     * @param cpuType
     * @param mainBoardType
     */
    public void makeComputer(int cpuType,int mainBoardType){
        //准备配件
        prepare(cpuType,mainBoardType);

    }

    public void makeComputer(AbstractFactory brand){
        //准备配件
        prepare(brand);

    }

    public void prepare(AbstractFactory brand){

        this.cpuApi = brand.createCpu();
        this.mainBoardApi = brand.createMainBoard();
        this.cpuApi.calculate();
        this.mainBoardApi.installCpu();
    }


    private void prepare(int cpuType, int mainBoardType) {

        this.cpuApi = CpuFactory.createCpu(cpuType);
        this.mainBoardApi = MainBoardFactory.createMainBoard(mainBoardType);
        this.cpuApi.calculate();
        this.mainBoardApi.installCpu();
    }

}
