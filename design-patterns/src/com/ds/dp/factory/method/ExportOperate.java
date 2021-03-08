package com.ds.dp.factory.method;

/**
 * @Author ds
 * @Date 2021/3/8 15:24
 * @Description
 */
public abstract class ExportOperate {
    /**
     * 导出文件
     * @param data
     * @return
     */
    public boolean export(String data){
        ExportFileApi api = factoryMethod();
        return api.export(data);
    }

    /**
     * 工厂方法，创建导出文件对象的接口对象
     * @return
     */
    protected abstract ExportFileApi factoryMethod();
}
