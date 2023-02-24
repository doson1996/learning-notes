package com.ds.dp.factory.method;

/**
 * @Author ds
 * @Date 2021/3/8 15:21
 * @Description 导出文本文件
 */
public class ExportTxt implements ExportFileApi{

    @Override
    public boolean export(String data) {
        System.out.println("导出" + data + "文本文件");
        return true;
    }
}
