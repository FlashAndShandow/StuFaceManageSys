package org.jdbc.controller;

import org.jdbc.dao.StuDAO;
import org.jdbc.model.Student;

public class ViewToDAO {

    public void add(Student stu) throws Exception{//通过DAO层添加学生
        StuDAO dao=new StuDAO();
        dao.addStu(stu);
    }
    public void update(Student stu) throws Exception{//通过DAO层更新学生
        StuDAO dao=new StuDAO();
        dao.updateStu(stu);
    }
    public void delete(int student_id) throws Exception{//通过DAO层删除学生
        StuDAO dao=new StuDAO();
        dao.delStu(student_id);
    }
    public void single_query(int student_id) throws Exception{//通过DAO层查询学生
        StuDAO dao=new StuDAO();
        dao.single_query(student_id);
    }
    public void multi_query() throws Exception{//通过DAO层查询所有学生
        StuDAO dao=new StuDAO();
        dao.multi_query();
    }
    public static void main(String[] args) {

    }
}
