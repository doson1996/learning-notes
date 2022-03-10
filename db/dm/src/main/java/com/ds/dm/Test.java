package com.ds.dm;

import java.sql.*;

/**
 * 数据库 达梦连接测试
 * @author ds
 */
public class Test {
    static String cname = "dm.jdbc.driver.DmDriver";
    static String url = "jdbc:dm://:5236?schema=ZMAGICUBE";
    static String userid = "SYSDBA";
    static String pwd = "SYSDBA";
    static Connection con = null;
    static Statement state = null;
    static ResultSet rs = null;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        try {
            //连接JDBC驱动程序
            System.out.println("Loading JDBC Driver...");
            Class.forName(cname);
            System.out.println("加载成功");
            //连接DM数据库
            System.out.println("Connecting to DM Server..");
            con = DriverManager.getConnection(url,userid,pwd);
            //通过连接对象创建java.sql.Statement对象
            state = con.createStatement();
            System.out.println("连接成功" + con);

            System.out.println("-----------------------");

            //定义插入SQL语句
            // String sql_insert1 = "insert into emp(empid,empname,deptid)values(1,'ceshi',10)";
            //String sql_insert2 = "insert into emp(empid,empname,deptid)values(2,'test',10)";
            //state.execute(sql_insert1);
            //state.execute(sql_insert2);
            System.out.println("插入成功");

            //定义删除SQL语句
            String sql_del = "delete  from emp where empid = 2";
            //   boolean b = state.execute(sql_del);
            System.out.println("删除成功");

            //修改
            String sql_update = "update emp set"+" empname = 'test' where empname = 'ceshi';";
            //  state.executeUpdate(sql_update);
            System.out.println("更新成功");

            //定义查询 SQL
            String sql_selectAll = "select * from oprt_name_info";
            //执行查询的 SQL 语句
            rs = state.executeQuery(sql_selectAll);
            displayResultSet(rs);


        }catch (ClassNotFoundException e){
            e.printStackTrace();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                //rs.close();
                state.close();
                con.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }


    public static void displayResultSet(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println(rs.getString(1)+ "  " + rs.getString(2) + " " + rs.getString(3));
        }
    }
}
