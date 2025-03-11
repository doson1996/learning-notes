package com.ds.mybatisx.session;

import java.io.InputStream;

import com.ds.mybatisx.config.Configuration;
import com.ds.mybatisx.config.build.XMLConfigBuilder;

/**
 * @author ds
 * @date 2023/8/8
 * @description
 */
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream) {
        // 解析配置文件
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder();
        Configuration configuration = xmlConfigBuilder.parse(inputStream);

        // 创建SqlSessionFactory工厂对象
        return new DefaultSqlSessionFactory(configuration);
    }

}
