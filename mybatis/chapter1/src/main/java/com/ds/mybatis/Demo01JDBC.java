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
public class Demo01JDBC {
    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://ds.com:3306/mybatisx","root","123456");
        PreparedStatement preparedStatement = connection.prepareStatement("select * from user;");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            System.out.println("id = " + id);
        }
        connection.close();
    }
}
