package com.ds.mybatis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author ds
 * @date 2023/8/7
 * @description jdbc
 */
public class Demo02JDBC {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://ds.com:3306/study", "root", "123456");
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO study.`user`(age, name) VALUES(?, ?);");
        for (int i = 0; i < 100; i++) {
            preparedStatement.setInt(1, i);
            preparedStatement.setString(2, "ds" + i);
            preparedStatement.execute();
        }
        connection.close();
    }
}
