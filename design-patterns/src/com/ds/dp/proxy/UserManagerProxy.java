package com.ds.dp.proxy;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author ds
 * @Date 2021/3/16 10:51
 * @Description 代理模式实现，一开始只返回id和name，在需要查看的时候再去查询deptNo，
 *              如果客户要查看每一条信息就需要查询1+N次数据库
 *
 *              适合大多少数据只展示一部分（id,name），点击查看详情(实际中不止deptNo,也许会是更多的数据)次数少的场景，
 *              既节约了内存，又减少了数据库操作
 */
public class UserManagerProxy {

    /**
     * 根据部门编号获取该部门所有员工
     * @param deptNo
     * @return
     */
    public List<UserApi> getByDeptNo(int deptNo){

        List<UserApi> result = new LinkedList<>();
        Connection connection = null;
        PreparedStatement ps = null;

        try {
            connection = getConnection();
            String sql = "SELECT id,name FROM proxy_user t WHERE t.dept_no LIKE ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1,deptNo + "%");
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                int userId = resultSet.getInt("id");
                String name = resultSet.getString("name");

               // User user = new User();
                Proxy proxy = new Proxy(new User());
                proxy.setUserId(userId);
                proxy.setName(name);

                result.add(proxy);
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
