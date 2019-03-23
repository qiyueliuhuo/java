package site.wzhe.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateData {
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
          UPDATE 表名称 SET 字段=值,字段=值,.. [WHERE 更新条件]
          问题：已经修改了数据库的编码方式为utf8,但select查询(terminal)得到的字段仍为??
          猜测：表也需要修改为utf8编码?
         */
        String sql = "UPDATE user SET Name='李四' WHERE Id=1";
        connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        // 定义数据库操作
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement(sql);
        int len = preparedStatement.executeUpdate();
        System.out.println("更新了" + len + "条记录.");
        // 数据库连接断开
        connection.close();
    }
}
