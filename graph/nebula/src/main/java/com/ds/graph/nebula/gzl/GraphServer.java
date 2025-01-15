package com.ds.graph.nebula.gzl;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author ds
 * @date 2025/1/15
 * @description 图平台服务
 */
public class GraphServer {


    public static List<String> list = Lists.newArrayList("图平台搜索服务", "图平台spark任务服务",
            "图平台个人库应用服务",
            "图平台调度服务",
            "图平台挖掘服务",
            "图平台分析服务",
            "图平台对象存储服务",
            "图平台构建服务",
            "图平台网关服务");

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
