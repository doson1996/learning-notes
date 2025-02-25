package com.ds.dp.proxy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author ds
 * @Date 2021/3/16 10:51
 * @Description 一次获取所有数据，访问数据过多内存使用太大
 */
public class UserManager {

    /**
     * 根据部门编号获取该部门所有员工
     *
     * @param deptNo
     * @return
     */
    public List<User> getByDeptNo(int deptNo) {

        List<User> result = new LinkedList<>();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = getConnection();
            String sql = "SELECT * FROM proxy_user t WHERE t.dept_no LIKE ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, deptNo + "%");
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int userId = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int dept = resultSet.getInt("dept_no");

                User user = new User();
                user.setId(userId);
                user.setName(name);
                user.setDeptNo(dept);
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return result;
    }

    /**
     * 获取数据库连接
     *
     * @return
     */
    private static Connection getConnection() {
        String url = "jdbc:mysql://192.168.33.130:3306/learning_note?useSSL=true";
        String user = "root";
        String password = "123456";

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("SQLException" + e.getMessage());
        }

        return connection;
    }
}
