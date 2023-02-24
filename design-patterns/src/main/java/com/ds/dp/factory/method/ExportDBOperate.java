package com.ds.dp.factory.method;

/**
 * @Author ds
 * @Date 2021/3/8 15:30
 * @Description
 */
public class ExportDBOperate extends ExportOperate{

    @Override
    protected ExportFileApi factoryMethod() {
        return new ExportDB();
    }
}
