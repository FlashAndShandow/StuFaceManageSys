package org.jdbc.model;

import java.sql.*;
import java.util.Scanner;

public class DBUtils {
     private static final String driver = "com.mysql.cj.jdbc.Driver";
    private static Connection connection = null;
    private static final String url = "jdbc:mysql://localhost:3306/StuManageSys?useSSL=false&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "glx20011230201";

    //静态代码块（将加载驱动、连接数据库放入静态块中）
    static{
        try {
            //1.加载驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            //2.获得数据库的连接
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    //对外提供一个方法来获取数据库连接
    public static Connection getConnection() throws SQLException {
        if (!connection.isClosed())
                System.out.println("Succeeded connecting to the Database!");
        return connection;
    }

    public static void main(String[] args) throws Exception{

        //3.通过数据库的连接操作数据库，实现增删改查
        Statement stmt = connection.createStatement();
        //import java.sql.ResultSet, 通过ResultSet来保存数据集
        ResultSet rs = stmt.executeQuery("select name,portrait_path from Students");
        while(rs.next()){//如果对象中有数据，就会循环打印出来
            System.out.println(rs.getString("name")+","+rs.getInt("portrait_path"));
        }
    }
}

//    public static void main(String[] args) throws ClassNotFoundException{
//
//            Class.forName(driver);
//            // Attempt to establish a connection to the database
//            connection = DriverManager.getConnection(url, user, password);
//            if (!connection.isClosed())
//                System.out.println("Succeeded connecting to the Database!");
//
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("请输入学生id:");
//            int id = scanner.nextInt();
//            System.out.println("请输入学生姓名:");
//            String name = scanner.next();
//            Statement statement = connection.createStatement();
//            String sql = "INSERT INTO stu_info values(" + id + ",'" + name + "')";
//            //执行sql语句
//            statement.executeUpdate(sql);
//
//            //关闭连接
//            connection.close();
//
//    }

//}

