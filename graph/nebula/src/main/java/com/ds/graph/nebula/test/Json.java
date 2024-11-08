package com.ds.graph.nebula.test;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;

/**
 * @author ds
 * @date 2024/11/5
 * @description
 */
public class Json {
    public static void main(String[] args) {
        String str = str();
        String[] split = str.split("\n");
        JSONObject data = new JSONObject();
        for (String s : split) {
            String[] split1 = s.split("\t");
            data.put(split1[0], split1[1]);
        }

        data.put("utime", DateUtil.now());
        data.put("ctime", DateUtil.now());
        System.out.println(data);
    }

    private static String str() {
        String str = "object_key\t重庆测试技术有限公司915000002024110501\n" +
                "entity_type\t有限责任公司\n" +
                "name\t重庆测试技术有限公司\n" +
                "capital\t1000.00\n" +
                "operation_startdate\t2011-08-23";

        return str;
    }
}
