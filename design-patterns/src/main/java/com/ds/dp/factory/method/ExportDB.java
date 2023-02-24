package com.ds.dp.factory.method;

/**
 * @Author ds
 * @Date 2021/3/8 15:22
 * @Description
 */
public class ExportDB implements ExportFileApi{

    @Override
    public boolean export(String data) {
        System.out.println("导出" + data + "数据库备份文件");
        return true;
    }
}
