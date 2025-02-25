package com.ds.dp.factory.method;

/**
 * @Author ds
 * @Date 2021/3/8 15:29
 * @Description 创建文本导出的具体对象
 */
public class ExportTxtOperate extends ExportOperate {
    @Override
    protected ExportFileApi factoryMethod() {
        return new ExportTxt();
    }
}
