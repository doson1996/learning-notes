package com.ds.dp.mediator;

/**
 * @Author ds
 * @Date 2021/3/15 15:01
 * @Description cd驱动
 */
public class CdDriver extends Colleague {

    public CdDriver(Mediator mediator) {
        super(mediator);
    }

    /**
     * 光驱读取出来的数据
     */
    private String data = "";

    /**
     * 获取光驱读取出来的数据
     *
     * @return
     */
    public String getData() {
        return data;
    }

    public void readCD() {
        this.data = "光驱数据1,光驱数据2";
        getMediator().change(this);
    }

}
