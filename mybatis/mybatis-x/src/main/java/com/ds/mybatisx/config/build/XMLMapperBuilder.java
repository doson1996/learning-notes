package com.ds.mybatisx.config.build;

import com.ds.mybatisx.config.Configuration;
import com.ds.mybatisx.mapping.MappedStatement;
import lombok.SneakyThrows;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

/**
 * @author ds
 * @date 2023/8/9
 * @description mapper解析
 */
public class XMLMapperBuilder {

    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    @SneakyThrows
    public void parse(InputStream inputStream) {
        SAXReader reader = new SAXReader();
        //2.加载xml
        Document document = reader.read(inputStream);
        //3.获取根节点
        Element rootElement = document.getRootElement();
        // 获取namespace
        String namespace = rootElement.attributeValue("namespace");

        List<Element> selectList = rootElement.selectNodes("//select");
        for (Element element : selectList) {
            String id = element.attributeValue("id");
            String parameterType = element.attributeValue("parameterType");
            String resultType = element.attributeValue("resultType");
            String sql = element.getTextTrim();
            // 封装MappedStatement
            MappedStatement statement = new MappedStatement();
            statement.setStatementId(namespace + "." + id);
            statement.setParameterType(parameterType);
            statement.setResultType(resultType);
            statement.setSql(sql);
            configuration.put(statement);
        }

        System.out.println("configuration = " + configuration);


    }
}
