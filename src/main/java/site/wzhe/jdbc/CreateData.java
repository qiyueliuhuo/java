package site.wzhe.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class CreateData {
    public static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DBURL = "jdbc:mysql://localhost:3306/test?characterEncoding=utf8&useSSL=false";
    public static final String DBUSER = "root";
    public static final String DBPASS = "123456";
    /*
     create table user( Id int primary key auto_increment,  Name varchar(18),  age int );
     alter table user add birthday DATETIME; # 生日
     alter table user change age Age int;
     alter table user change birthday Birthday DATETIME;
     describe user;
     */

    public static void main(String[] args) throws Exception{
        // createOneData();
        createManyData(Long.valueOf((long)10000));

    }
    // TODO insertBatch()
    public static void createManyData(Long totalNum) throws Exception {
        Connection connection = null;
        Class.forName(DBDRIVER);
        String sql = "INSERT INTO users(id,name,age,birthday) VALUES "
                + " (0, ?, ?, ?)";

        PreparedStatement preparedStatement = null;
        Long successNum = new Long(0);
        for (int i = 0; i < totalNum; i++) {
            connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "zhangsan");
            preparedStatement.setInt(2, 34 + i);
            preparedStatement.setDate(3, new Date(0));
            int len = preparedStatement.executeUpdate();
            if (len > 0) {
                successNum = successNum + 1;
            }
            connection.close();
        }
        System.out.println("插入了" + successNum + "条记录.");
    }
    public static void createOneData() throws Exception {
        Connection connection = null;
        Class.forName(DBDRIVER);

        //String sql = "INSERT INTO user(Id,Name,Age,Birthday) VALUES"
        //   + " (0, 'wangzhe', 20, '1996-06-21') ";
        String sql = "INSERT INTO users(id,name,age,birthday) VALUES "
                + " (0, ?, ?, ?)";
        /*
        在开发中 100%不会使用 Statement 进行操作,而都使用其子接口 PreparedStatement 完成.
        如果要创建 PreparedStatement 接口的对象需要依靠 Connection 接口中的 prepareStatement()方法完成,而且必须传入
        一个预处理的 SQL 语句,所有的占位符使用“?”表示.
        */
        connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        // 定义数据库操作
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1, "zhangsan");
        preparedStatement.setInt(2,34);
        preparedStatement.setDate(3,new Date(0));

        int len = preparedStatement.executeUpdate();
        System.out.println("插入了" + len + "条记录.");

        // 资源释放
        preparedStatement.close();
        // 数据库连接断开
        connection.close();
    }
}
