package site.wzhe.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteData {
    public static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://localhost:3306/test";
    public static final String DBUSER = "root";
    public static final String DBPASS = "wangzhe";
    /*
     */
    public static void main(String[] args) throws Exception{
        Connection connection = null;
        Class.forName(DBDRIVER);
        /*
        DELETE FROM 表名称 [WHERE 删除条件]
         */
        String sql = "DELETE FROM user WHERE Id=1";
        connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        // 定义数据库操作
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement(sql);
        int len = preparedStatement.executeUpdate();
        System.out.println("删除了" + len + "条记录.");
        // 数据库连接断开
        connection.close();
    }
}
