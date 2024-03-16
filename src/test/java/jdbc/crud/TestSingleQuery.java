package jdbc.crud;

import org.jdbc.dao.StuDAO;
import org.jdbc.model.Student;

public class TestSingleQuery {
    public static void main(String[] args) {

        StuDAO dao = new StuDAO();
        try {
            Student stu = dao.single_query(1);
            System.out.println(stu.getName()+","+stu.getPortrait_path());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
