package com.ds.mybatisx.executor;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import com.ds.mybatisx.config.Configuration;
import com.ds.mybatisx.mapping.MappedStatement;
import com.ds.mybatisx.util.BoundSql;
import com.ds.mybatisx.util.GenericTokenParser;
import com.ds.mybatisx.util.ParameterMapping;
import com.ds.mybatisx.util.ParameterMappingTokenHandler;
import lombok.SneakyThrows;

/**
 * @author ds
 * @date 2023/8/9
 * @description
 */
public class SimpleExecutor implements Executor {
    private final Configuration configuration;
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public SimpleExecutor(Configuration configuration) {
        this.configuration = configuration;
    }

    @SneakyThrows
    @Override
    public <E> List<E> doQuery(String statementId, Object parameter) {
        MappedStatement statement = configuration.get(statementId);
        // select * from user where id = #{id};
        // 替换sql select * from user where id = ?;  #{id}保存下来
        String sql = statement.getSql();
        BoundSql boundSql = getBoundSql(sql);
        String parameterType = statement.getParameterType();
        String resultType = statement.getResultType();
        connection = configuration.getDataSource().getConnection();
        preparedStatement = connection.prepareStatement(boundSql.getFinalSql());
        // 解析参数
        if (parameter != null)
            parseParameter(preparedStatement, parameterType, parameter, boundSql);

        resultSet = preparedStatement.executeQuery();
        // 处理返回结果
        Class<?> resultClazz = Class.forName(resultType);
        ArrayList<E> result = new ArrayList<>();
        while (resultSet.next()) {
            // 元数据信息 包含字段名，字段的值
            ResultSetMetaData metaData = resultSet.getMetaData();
            Object resultBean = resultClazz.newInstance();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                String columnName = metaData.getColumnName(i);
                Object value = resultSet.getObject(columnName);
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(columnName, resultClazz);
                Method writeMethod = propertyDescriptor.getWriteMethod();
                writeMethod.invoke(resultBean, value);
            }
            result.add((E) resultBean);
        }
        return result;
    }

    /**
     * todo 中文乱码
     *
     * @param preparedStatement
     * @param parameterType
     * @param parameter
     * @param boundSql
     * @throws Exception
     */
    private void parseParameter(PreparedStatement preparedStatement, String parameterType, Object parameter, BoundSql boundSql) throws Exception {
        if (parameterType == null)
            return;
        Class<?> paramClazz = Class.forName(parameterType);
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        for (int i = 0; i < parameterMappings.size(); i++) {
            // 字段名
            String filed = parameterMappings.get(i).getContent();
            Field declaredField = paramClazz.getDeclaredField(filed);
            declaredField.setAccessible(true);
            Object value = declaredField.get(parameter);
            preparedStatement.setObject(i + 1, value);
        }
    }

    private BoundSql getBoundSql(String sql) {
        ParameterMappingTokenHandler parameterMappingTokenHandler = new ParameterMappingTokenHandler();
        GenericTokenParser genericTokenParser = new GenericTokenParser("#{", "}", parameterMappingTokenHandler);
        String finalSql = genericTokenParser.parse(sql);
        List<ParameterMapping> parameterMappings = parameterMappingTokenHandler.getParameterMappings();
        return new BoundSql(finalSql, parameterMappings);
    }

    @SneakyThrows
    @Override
    public void close() {
        if (resultSet != null)
            resultSet.close();

        if (preparedStatement != null)
            preparedStatement.close();

        if (connection != null) {
            connection.close();
        }
    }
}
