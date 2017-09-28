package com.yll.demo.jdbc;

import com.yll.sharde.util.ReleaseUtil;

import java.sql.*;

/**
 * @author：linlin.yang
 * @date：2017/9/12 18:49
 */
public class JdbcExample {
    static final String DRIVER = "com.mysql.jdbc.Driver";
    static final String URL = "jdbc:mysql://localhost:3306/sharde";
    static final String USER = "root";
    static final String PASSWORD = "123456";

    public static void main(String[] args) throws SQLException {
//        handleByStatement();
        handleByPreparedStatement();
    }

    public static void handleByStatement() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            //注册JDBC驱动
            Class.forName(DRIVER);
            //获取一个数据库连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            //执行查询
            stmt = conn.createStatement();
            String sql = "select * from user";
            resultSet = stmt.executeQuery(sql);

            //读取结果集
            while (resultSet.next()) {
                Integer userId = resultSet.getInt("user_id");
                String name = resultSet.getString("name");
                Integer age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                System.out.println(userId + ", " + name + ", " + age + ", " + address);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("register jdbc driver error");
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ReleaseUtil.close(resultSet);
            ReleaseUtil.close(stmt);
            ReleaseUtil.close(conn);
        }
    }

    public static void handleByPreparedStatement() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        Savepoint savepoint = null;
        try {
            //注册JDBC驱动
            Class.forName(DRIVER);
            //获取一个数据库连接
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            //关闭自动提交
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement("update user set name='user1' where user_id=?");
            stmt.setInt(1, 1);
            stmt.executeUpdate();

            //添加检查点
            savepoint = conn.setSavepoint("savepoint1");

            stmt = conn.prepareStatement("update user set name='duomu' where user_id=?");
            stmt.setInt(1, 1);
            stmt.executeUpdate();

            stmt = conn.prepareStatement("insert into user(name) values('user0')");
            stmt.execute();

            //模拟异常
//            int i = 1 / 0;

            //批处理操作
            String sql1 = "insert into user(name) values('user1')";
            String sql2 = "insert into user(name) values('user2')";
            String sql3 = "insert into user(name) values('user3')";
            String sql4 = "insert into user(name) values('user4')";
            String sql5 = "insert into user(name) values('user5')";
            stmt.addBatch(sql1);
            stmt.addBatch(sql2);
            stmt.addBatch(sql3);
            stmt.addBatch(sql4);
            stmt.addBatch(sql5);
            int[] count = stmt.executeBatch();
            System.out.println("batch insert：" + count.length);

            //执行查询
            String sql = "select * from user where user_id=?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, 1);
            resultSet = stmt.executeQuery();

            //读取结果集
            while (resultSet.next()) {
                Integer userId = resultSet.getInt("user_id");
                String name = resultSet.getString("name");
                Integer age = resultSet.getInt("age");
                String address = resultSet.getString("address");
                System.out.println(userId + ", " + name + ", " + age + ", " + address);
            }
            conn.commit();
        } catch (ClassNotFoundException e) {
            System.out.println("register jdbc driver error");
            e.printStackTrace();
            conn.rollback(savepoint);
        } catch (SQLException e) {
            e.printStackTrace();
            conn.rollback(savepoint);
        } catch (Exception e) {
            conn.rollback(savepoint);
        } finally {
            ReleaseUtil.close(resultSet);
            ReleaseUtil.close(stmt);
            ReleaseUtil.close(conn);
        }
    }

}
