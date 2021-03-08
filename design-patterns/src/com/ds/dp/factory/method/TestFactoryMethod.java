package com.ds.dp.factory.method;

/**
 * @Author ds
 * @Date 2021/3/8 15:31
 * @Description 工厂方法模式
 */
public class TestFactoryMethod {

    public static void main(String[] args) {
        ExportOperate exportDB = new ExportDBOperate();
        exportDB.export("导出测试数据");

        ExportOperate exportTxt = new ExportTxtOperate();
        exportTxt.export("导出测试数据");

    }
}
