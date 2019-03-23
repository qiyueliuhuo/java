package site.wzhe.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class QueryData {
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
        String sql = "SELECT * FROM user";
        connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        // 定义数据库操作
        PreparedStatement preparedStatement = null;
        // 保存查询结果
        ResultSet resultSet = null;
        preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt("Id");
            String name = resultSet.getString("Name");
            int age = resultSet.getInt("Age");
            String date = resultSet.getString("Birthday");
            System.out.println("User Id=" + id + " Name=" + name + " age=" + age + " birthday=" + date);
        }
        System.out.println("查询成功");
        // 资源释放
        resultSet.close();
        preparedStatement.close();
        // 数据库连接断开
        connection.close();
    }
}
