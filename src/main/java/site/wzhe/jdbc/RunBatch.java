package site.wzhe.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class RunBatch {
    public static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://localhost:3306/test";
    public static final String DBUSER = "root";
    public static final String DBPASS = "wangzhe";
    /*
    在使用批处理的操作中发现,如果中间有一条语句出错了,则默认情况下是将出错之前的代码进行提交,这是由于
    JDBC 采用了自动的事务提交的方式才造成的结果。
    如果此时要进行事务处理的话,则需要按照如下的方式进行:
    1.取消自动提交:public void setAutoCommit(boolean autoCommit) throws SQLException
    2.执行更新操作:
    3.如果没有错误,则提交事务:public void commit() throws SQLException
    4.如果有错误,则进行回滚:public void rollback() throws SQLException
     */
    public static void main(String[] args) throws Exception{
        Connection connection = null;
        Class.forName(DBDRIVER);

        String sql1 = "UPDATE user SET Name='lisi' WHERE Id=3";
        String sql2 = "UPDATE user SET sName='wangwu' WHERE Id=100";
        connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        // 定义数据库操作
        Statement statement = null;  // 定义数据库操作
        connection.setAutoCommit(false); // 取消自动提交(默认为自动提交)
        statement = connection.createStatement(); // 创建数据库操作
        try {
            statement.addBatch(sql1);
            statement.addBatch(sql2); // 等等多条操作

            // 更新操作返回更新的记录数
            int len[] = statement.executeBatch();
            System.out.println("更新了" + len.length + "条记录.");
            connection.commit();
        }catch (Exception e) {
            connection.rollback();  //  如何做到回滚？？？？？
            System.out.println("已回滚");
        }
        // 数据库连接断开
        connection.close();
    }
}
