package com.ds.mybatisx.executor;

import com.ds.mybatisx.config.Configuration;
import com.ds.mybatisx.mapping.MappedStatement;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        String sql = statement.getSql();
        String parameterType = statement.getParameterType();
        String resultType = statement.getResultType();
        Connection connection = configuration.getDataSource().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            System.out.println("id = " + id);
        }
        return null;
    }

    @Override
    public void close() {
        System.out.println("close...");
    }
}
