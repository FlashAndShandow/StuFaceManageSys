package jdbc.crud;

import org.jdbc.dao.StuDAO;
import org.jdbc.model.Student;

import java.util.List;


public class TestMultipleQuery {
    public static void main(String[] args) throws Exception {
        StuDAO sd=new StuDAO();
        List<Student> stus=sd.multi_query();
        //遍历集合，并打印姓名和头像路径
        for (Student student : stus) {
            System.out.println(student.getName()+","+student.getPortrait_path());
        }
    }
}



