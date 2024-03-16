package jdbc.crud;

import org.jdbc.dao.StuDAO;
import org.jdbc.model.Student;

public class TestDel {
    public static void main(String[] args) {
        Student stu = new Student();
        stu.setStudent_id(1);
        StuDAO dao = new StuDAO();
        try {
            dao.delStu(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
