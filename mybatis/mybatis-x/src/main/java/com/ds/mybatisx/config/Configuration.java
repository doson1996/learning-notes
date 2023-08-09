package com.ds.mybatisx.config;

import com.ds.mybatisx.mapping.MappedStatement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ds
 * @date 2023/8/8
 * @description
 */
public class Configuration {

    private DataSource dataSource;

    private Map<String, MappedStatement> mappedStatements = new HashMap<>();

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void put(MappedStatement statement) {
        mappedStatements.put(statement.getStatementId(), statement);
    }

    public MappedStatement get(String statementId) {
        return mappedStatements.get(statementId);
    }

}
