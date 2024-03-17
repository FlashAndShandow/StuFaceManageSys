package jdbc.crud;

import com.guo.jdbc.dao.StuDAO;
import com.guo.jdbc.model.Student;

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
