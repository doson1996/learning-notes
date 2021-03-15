package com.ds.dp.mediator;

/**
 * @Author ds
 * @Date 2021/3/15 14:39
 * @Description 中介者模式  本质：封装交互
 *              优点：
 *                  1.松散耦合
 *                  2.集中控制交互
 *              缺点
 *                  1.过于集中
 *                  2.交互较多和复杂时，难以管理和维护
 */
public class MediatorClient {

    public static void main(String[] args) {
        MotherBoard motherBoard = new MotherBoard();
        CdDriver cdDriver = new CdDriver(motherBoard);
        Cpu cpu = new Cpu(motherBoard);
        SoundCard soundCard = new SoundCard(motherBoard);

        motherBoard.setCdDriver(cdDriver);
        motherBoard.setCpu(cpu);
        motherBoard.setSoundCard(soundCard);
        cdDriver.readCD();

    }
}
