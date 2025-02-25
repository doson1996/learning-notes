package com.ds.dp.mediator;

/**
 * @Author ds
 * @Date 2021/3/15 15:01
 * @Description cpu
 */
public class Cpu extends Colleague {

    public Cpu(Mediator mediator) {
        super(mediator);
    }

    private String soundData = "";

    private String cpuData = "";

    public void process(String data) {
        System.out.println("cpu处理数据--" + data);
        String[] ss = data.split(",");
        this.soundData = ss[0];
        this.cpuData = ss[1];
        //通知主板，cpu工作完成
        this.getMediator().change(this);
    }

    public String getSoundData() {
        return soundData;
    }

    public String getCpuData() {
        return cpuData;
    }

}
