package com.ds.dp.singleton;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author ds
 * @Date 2021/3/8 14:32
 * @Version 1.0
 * @Description 控制实例数
 *              这里设置为两个实例，不是线程安全的
 */
public class OneExtend {

    private static final String DEFAULT_KEY = "Cache";

    /**
     * 控制实例数
     */
    private static int num = 1;

    /**
     * 最大实例数
     */
    private static final int MAX_NUM = 2;

    /**
     * 缓存实例
     */
    private static Map<String,OneExtend> cache = new HashMap<>();


    public static OneExtend getInstance(){

        String key = DEFAULT_KEY + num;
        OneExtend oneExtend = cache.get(key);
        if(oneExtend == null){
            oneExtend = new OneExtend();
            cache.put(key,oneExtend);
        }

        num++;

        if(num > MAX_NUM){
            num = 1;
        }

        return oneExtend;
    }



}
