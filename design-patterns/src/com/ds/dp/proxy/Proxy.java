package com.ds.dp.proxy;

import java.sql.*;

/**
 * @Author ds
 * @Date 2021/3/16 11:53
 * @Description
 */
public class Proxy implements UserApi{

    private User user = null;

    public Proxy(User user){
        this.user = user;
    }


    /**
     * 标志是否重新加载过数据
     */
    private boolean loaded = false;

    /**
     * 重新加载数据
     */
    private void reload() {

        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = getConnection();
            String sql = "SELECT * FROM proxy_user t WHERE t.id = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1,getUserId());
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
               // int userId = resultSet.getInt("id");
               // String name = resultSet.getString("name");
                int dept = resultSet.getInt("dept_no");

               // user.setId(userId);
               // user.setName(name);
                user.setDeptNo(dept);
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

    }

    @Override
    public int getUserId() {
        return user.getId();
    }

    @Override
    public void setUserId(int id) {
        user.setId(id);
    }

    @Override
    public String getName() {
        return user.getName();
    }

    @Override
    public void setName(String name) {
        user.setName(name);
    }

    @Override
    public int getDeptNo() {

        if( !this.loaded){
            reload();
            loaded = true;
        }

        return user.getDeptNo();
    }

    @Override
    public void setDeptNo(int deptNo) {
        user.setDeptNo(deptNo);
    }

    /**
     * 获取数据库连接
     * @return
     */
    private static Connection getConnection(){
        String url = "jdbc:mysql://192.168.33.130:3306/learning_note?useSSL=true";
        String user = "root";
        String password = "123456";

        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){
            System.out.println("SQLException" + e.getMessage());
        }

        return connection;
    }
}
