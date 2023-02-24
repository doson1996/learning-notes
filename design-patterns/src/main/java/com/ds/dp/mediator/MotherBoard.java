package com.ds.dp.mediator;

/**
 * @Author ds
 * @Date 2021/3/15 15:06
 * @Description 主板
 */
public class MotherBoard implements Mediator{

    private CdDriver cdDriver = null;

    private Cpu cpu = null;

    private SoundCard soundCard = null;

    public void setCdDriver(CdDriver cdDriver){
        this.cdDriver = cdDriver;
    }

    public void setCpu(Cpu cpu){
        this.cpu = cpu;
    }

    public void setSoundCard(SoundCard soundCard){
        this.soundCard = soundCard;
    }

    @Override
    public void change(Colleague colleague) {
        if (colleague == cdDriver){
            this.openCdDriverReadData((CdDriver) colleague);
        }else if(colleague == cpu){
            this.openCpu((Cpu) colleague);
        }

    }

    private void openCdDriverReadData(CdDriver cdDriver){
        //充光驱获取data
        String data = cdDriver.getData();
        System.out.println("CD data=" + data);
        this.cpu.process(data);
    }

    private void openCpu(Cpu cpu){
        String cpuData = cpu.getCpuData();
        String soundData = cpu.getSoundData();

        this.soundCard.soundData(soundData);
    }
}
