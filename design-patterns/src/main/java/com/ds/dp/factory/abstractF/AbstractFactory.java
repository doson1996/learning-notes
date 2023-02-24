package com.ds.dp.factory.abstractF;

/**
 * @Author ds
 * @Date 2021/3/12 9:56
 * @Description 抽象工厂
 */
public interface AbstractFactory {

    /**
     * 创建cpu
     * @return
     */
    CpuApi createCpu();

    /**
     * 创建主板
     * @return
     */
    MainBoardApi createMainBoard();
}

class Dell01 implements AbstractFactory{

    static {
        System.out.println("dell--------");
    }

    @Override
    public CpuApi createCpu() {
        return new IntelCpu(10);
    }

    @Override
    public MainBoardApi createMainBoard() {
        return new MsiMainBoard(10);
    }
}

class Hp01 implements AbstractFactory{

    static {
        System.out.println("hp--------");
    }

    @Override
    public CpuApi createCpu() {
        return new IntelCpu(20);
    }

    @Override
    public MainBoardApi createMainBoard() {
        return new GaMainBoard(20);
    }
}