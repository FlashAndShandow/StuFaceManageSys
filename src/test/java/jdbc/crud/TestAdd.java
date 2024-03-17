package jdbc.crud;

import java.util.Date;

import com.guo.jdbc.dao.StuDAO;
import com.guo.jdbc.model.Student;
public class TestAdd {
    public static void main(String[] args) {
        Student stu = new Student();
        stu.setStudent_id(1);
        stu.setName("张三");
        stu.setIdentity_number("123456");
        stu.setPortrait_path(1);
        stu.setCreated_at(new Date());
        stu.setUpdated_at(new Date());
        StuDAO dao = new StuDAO();
        try {
            dao.addStu(stu);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

