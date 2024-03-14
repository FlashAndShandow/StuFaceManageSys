package org.jdbc.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import org.jdbc.model.DBUtils;
import org.jdbc.model.Student;

public class StuDAO {
    /**
     * 使用prepareStatement将SQL语句加载到驱动程序connection集成程序中，但是并不直接执行
     * 而是当它调用execute()方法的时候才真正执行；
     *
     * SQL语句中的参数用?表示占位符，最后会把SQL语句拼接完整才执行，这样就会减少对数据库的操作
     */
    public void addStu(Student stu) throws Exception{//添加学生

        Timestamp sqlCreatedDate = new Timestamp(stu.getCreated_at().getTime());
        Timestamp sqlUpdatedDate = new Timestamp(stu.getUpdated_at().getTime());


        Connection connection=DBUtils.getConnection();//首先拿到数据库的连接
        String sql="" +
                "insert into Students"+
                "(student_id,name,identity_number,portrait_path,"+
                "created_at,updated_at) "+
                "values("+
                "?,?,?,?,?,?)";//参数用?表示占位符;用mysql的日期函数current_date()来获取当前日期
        //预编译sql语句
        PreparedStatement psmt = connection.prepareStatement(sql);
        //先对应SQL语句，给SQL语句传递参数
        psmt.setInt(1, stu.getStudent_id());
        psmt.setString(2, stu.getName());
        psmt.setString(3, stu.getIdentity_number());
        psmt.setInt(4, stu.getPortrait_path());
        psmt.setTimestamp(5, sqlCreatedDate);
        psmt.setTimestamp(6, sqlUpdatedDate);

        //执行SQL语句
        psmt.execute();

    }


    public void updateStu(Student stu) throws Exception{//更新学生

        Connection connection=DBUtils.getConnection();//首先拿到数据库的连接
        String sql="" +
                "update Students "+
                "set student_id=?,name=?,identity_number=?,portrait_path=?,"+
                "created_at=?,updated_at=? "+
                "where student_id=?;";//用mysql的日期函数current_date()来获取当前日期
        //预编译sql语句
        PreparedStatement psmt = connection.prepareStatement(sql);
        //先对应SQL语句，给SQL语句传递参数
        //注意：setDate()函数第二个参数需要的是java.sql.Date类型，由于传进来的是java.util.Date，类型不符，需要做一下转换
        //psmt.setDate(4, new Date(g.getBirthday().getTime()));
        psmt.setInt(1, stu.getStudent_id());
        psmt.setString(2, stu.getName());
        psmt.setString(3, stu.getIdentity_number());
        psmt.setInt(4, stu.getPortrait_path());
        psmt.setTimestamp(5, new Timestamp(stu.getCreated_at().getTime()));
        psmt.setTimestamp(6, new Timestamp(stu.getUpdated_at().getTime()));
        psmt.setInt(7, stu.getStudent_id());
        //执行SQL语句
        psmt.execute();
    }

    public void delStu(Student stu) throws Exception{ //删除学生

        Connection connection = DBUtils.getConnection();//首先拿到数据库的连接
        String sql="" +
                "delete from Students "+
                "where id=?";//参数用?表示，相当于占位符;用mysql的日期函数current_date()来获取当前日期
        //预编译sql语句
        PreparedStatement psmt = connection.prepareStatement(sql);
        //先对应SQL语句，给SQL语句传递参数
        psmt.setInt(1, stu.getStudent_id());
        //执行SQL语句
        psmt.execute();

    }

    public List<Student> query() throws Exception{
        Connection con= DBUtils.getConnection();
        Statement stmt=con.createStatement();
        ResultSet rs=stmt.executeQuery("select name,portrait_path from Students");
        List<Student> stus=new ArrayList<Student>();
        Student stu=null;
        while(rs.next()){//如果对象中有数据，就会循环打印出来
            stu=new Student();
            stu.setName(rs.getString("name"));
            stu.setPortrait_path(rs.getInt("portrait_path"));
            stus.add(stu);
        }
        return stus;
    }
    //查询单个学生
    public Student get(){
        return null;
    }
}



