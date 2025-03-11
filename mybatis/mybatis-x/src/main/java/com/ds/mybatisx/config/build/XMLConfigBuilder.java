package com.ds.mybatisx.config.build;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import com.alibaba.druid.pool.DruidDataSource;
import com.ds.mybatisx.config.Configuration;
import com.ds.mybatisx.io.Resources;
import lombok.SneakyThrows;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author ds
 * @date 2023/8/8
 * @description config解析
 */
public class XMLConfigBuilder {

    private final Configuration configuration;

    public XMLConfigBuilder() {
        this.configuration = new Configuration();
    }

    @SneakyThrows
    public Configuration parse(InputStream inputStream) {
        SAXReader reader = new SAXReader();
        //2.加载xml
        Document document = reader.read(inputStream);
        //3.获取根节点
        Element rootElement = document.getRootElement();
        List<Element> list = rootElement.selectNodes("//property");
        Properties properties = new Properties();
        for (Element element : list) {
            String name = element.attributeValue("name");
            String value = element.attributeValue("value");
            properties.put(name, value);
        }

        // 创建数据源对象
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(properties.getProperty("driver"));
        dataSource.setUrl(properties.getProperty("url"));
        dataSource.setUsername(properties.getProperty("user"));
        dataSource.setPassword(properties.getProperty("password"));
        configuration.setDataSource(dataSource);

        // 解析mapper
        List<Element> mappers = rootElement.selectNodes("//mapper");
        for (Element mapper : mappers) {
            String mapperPath = mapper.attributeValue("resource");
            InputStream resourceAsStream = Resources.getResourceAsStream(mapperPath);
            XMLMapperBuilder xmlMapperBuilder = new XMLMapperBuilder(configuration);
            xmlMapperBuilder.parse(resourceAsStream);
        }

        return configuration;
    }
}
