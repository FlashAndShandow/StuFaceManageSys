package jdbc;

import org.jdbc.dao.StuDAO;
import org.jdbc.model.Student;

import java.util.Date;

public class TestUpdate {
    public static void main(String[] args) {
        Student stu = new Student();
        stu.setStudent_id(1);
        stu.setName("李四");
        stu.setIdentity_number("654321");
        stu.setPortrait_path(123);
        stu.setCreated_at(new Date());
        stu.setUpdated_at(new Date());
        StuDAO dao = new StuDAO();
        try {
            dao.updateStu(stu);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
