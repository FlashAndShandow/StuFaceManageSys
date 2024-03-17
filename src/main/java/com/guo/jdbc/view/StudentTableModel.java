package com.guo.jdbc.view;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class StudentTableModel extends DefaultTableModel {

    //  创建一个单例类专门用来给表格赋值
    static Vector<String> columns = new Vector<>();
    private static final StudentTableModel stm = new StudentTableModel();

    static {
        columns.addElement("学生ID");
        columns.addElement("姓名");
        columns.addElement("身份证号");
        columns.addElement("头像");
        columns.addElement("创建时间");
        columns.addElement("更新时间");
    }

    private StudentTableModel() {
        super(null, columns);
    }

    public static StudentTableModel assembleModel(Vector<Vector<Object>> data) {
        stm.setDataVector(data, columns);// 学生数据模型传入数据后返回
        return stm;
    }

    public static Vector<String> getColumns() {//给columns设置一个get方法，让外界能获取columns接口
        return columns;
    }

    @Override
    public boolean isCellEditable(int row, int column) {//设置不可编辑
        return false;
    }
}
