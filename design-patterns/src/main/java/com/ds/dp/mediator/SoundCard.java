package com.ds.dp.mediator;

/**
 * @Author ds
 * @Date 2021/3/15 15:01
 * @Description 声卡
 */
public class SoundCard extends Colleague {

    public SoundCard(Mediator mediator) {
        super(mediator);
    }


    public void soundData(String data) {
        System.out.println("播放声音" + data);
    }

}
