package com.ds.graph.nebula.gzl;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author ds
 * @date 2025/1/15
 * @description 风铃服务
 */
public class IapServer {

    public static List<String> list = Lists.newArrayList("风铃用户服务",
            "风铃实时服务",
            "风铃关注服务",
            "风铃搜索服务");

    public static void main(String[] args) {
        String str = "后端：图平台搜索服务迁移到国产服务器\t0.5\n" +
                "后端：图平台搜索服务适配TDSQL表结构修改\t2\n" +
                "后端：图平台搜索服务适配TDSQL SQL修改\t4\n" +
                "后端：图平台搜索服务适配TDSQL数据迁移\t2";

        for (String s : list) {
            System.out.println(str.replaceAll("图平台搜索服务", s));
        }
    }

}
