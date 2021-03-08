package com.ds.dp.factory.method;

/**
 * @Author ds
 * @Date 2021/3/8 15:17
 * @Version 1.0
 * @Description 导出文件接口
 */
public interface ExportFileApi {

    /**
     * 导出文件
     * @param data 要导出的数据
     * @return 是否导出成功
     */
    public boolean export(String data);
}
