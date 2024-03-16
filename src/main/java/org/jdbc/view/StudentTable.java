package org.jdbc.view;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.Vector;

public class StudentTable extends JTable {
    public StudentTable() {
        //设置表头
        JTableHeader tableHeader = getTableHeader();//获取表头
        tableHeader.setFont(new Font(null, Font.BOLD, 16));//设置表头的字体
        tableHeader.setForeground(Color.RED);//设置表头的前景色
        // 设置表头文字居中显示



        //设置表格主体
        setFont(new Font(null, Font.PLAIN, 14));//设置表格的字体
        setForeground(Color.BLACK);//设置表格的前景色
        setGridColor(Color.GRAY);//设置表格的网格颜色
        setRowHeight(30);

        //设置可以多行选择
        setRowSelectionAllowed(true);

    }

    public void renderRule() {
        //表格的渲染方式

        Vector<String> columns = StudentTableModel.getColumns();//获取学生表格的列名
        for (int i = 0; i < columns.size(); i++) {
            getColumn(columns.get(i)).setCellRenderer(new StudentCellRenderer());//设置表格列的渲染方式
            if (i == 0) {
                getColumn(columns.get(i)).setPreferredWidth(50);//设置学生ID列的宽度
            }
        }

    }

}

class StudentCellRenderer extends DefaultTableCellRenderer {
    //一个单元格渲染器，用来渲染表格中的单元格

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        if (row % 2 == 1) {// 奇数行变灰色，偶数行为白色
            setBackground(Color.LIGHT_GRAY);
        } else {
            setBackground(Color.WHITE);
        }

        setHorizontalAlignment(DefaultTableCellRenderer.CENTER);//设置单元格内容居中
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
class StudentTableModel extends DefaultTableModel {

    //  创建一个单例类专门用来给表格赋值
    static Vector<String> columns = new Vector<>();
    private static StudentTableModel stm = new StudentTableModel();

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

