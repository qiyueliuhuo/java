package site.wzhe.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionJDBC {
    // 驱动程序(其实就是一个类), mysql数据库厂商根据java中jdbc(jdk database connect)接口标准, 实现自己的jdbc
    // 猜测：jdbc实现类与mysql数据库进行通信,实现java中访问数据库mysql
    public static final String DBDRIVER = "com.mysql.cj.jdbc.Driver";
    // 连接地址是由各个数据库生产商单独提供的,需要单独记住: url中有mysql
    // JDBC 网络连接:通过网络协议进行数据库的连接操作,猜测:jdbc使用底层socket网络通信来连接到
    // 数据库服务器,服务器在一直监听着3306端口
    public static final String DBURL = "jdbc:mysql://localhost:3306/test";
    // 连接数据库的用户名
    public static final String DBUSER = "root";
    // 连接数据库的密码
    public static final String DBPASS = "wangzhe";

    public static void main(String[] args) throws Exception{
        // 数据库连接对象
        Connection connection = null;
        // 类加载器加载驱动程序
        Class.forName(DBDRIVER);
        // 获得连接
        connection = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        System.out.println(connection);
        connection.close();
    }
}
