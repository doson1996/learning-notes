package com.ds.dp.prototype.sample;

/**
 * @Author ds
 * @Date 2021/3/15 11:48
 * @Description 使用原型的客户端
 */
public class Client {

    /**
     * 持有需要使用的原型接口对象
     */
    private Prototype prototype;

    /**
     * 构造方法，传入需要使用的原型接口对象
     * @param prototype
     */
    public Client(Prototype prototype){
        this.prototype = prototype;
    }

    /**
     * 示意方法
     */
    public void operate(){

        //需要创建原型接口的对象
        Prototype newPrototype = prototype.clone();
    }
}
