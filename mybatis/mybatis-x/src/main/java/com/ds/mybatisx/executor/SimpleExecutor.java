package com.ds.mybatisx.executor;

import com.ds.mybatisx.config.Configuration;
import com.ds.mybatisx.mapping.MappedStatement;
import com.ds.mybatisx.util.BoundSql;
import com.ds.mybatisx.util.GenericTokenParser;
import com.ds.mybatisx.util.ParameterMapping;
import com.ds.mybatisx.util.ParameterMappingTokenHandler;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author ds
 * @date 2023/8/9
 * @description
 */
public class SimpleExecutor implements Executor {

    private final Configuration configuration;

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
        Connection connection = configuration.getDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(boundSql.getFinalSql());
        // 解析参数
        parseParameter(preparedStatement,parameterType,parameter, boundSql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            System.out.println("id = " + id);
        }
        return null;
    }

    /**
     * todo 中文乱码
     * @param preparedStatement
     * @param parameterType
     * @param parameter
     * @param boundSql
     * @throws Exception
     */
    private void parseParameter(PreparedStatement preparedStatement, String parameterType, Object parameter, BoundSql boundSql) throws Exception {
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

    @Override
    public void close() {
        System.out.println("close...");
    }
}
