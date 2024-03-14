package org.jdbc.controller;

import org.jdbc.dao.StuDAO;
import org.jdbc.model.Student;

import java.util.List;


public class StudentActions {
    public static void main(String[] args) throws Exception {
        StuDAO sd=new StuDAO();
        List<Student> stus=sd.query();
        //遍历集合，并打印姓名和头像路径
        for (Student student : stus) {
            System.out.println(student.getName()+","+student.getPortrait_path());
        }
    }
}



