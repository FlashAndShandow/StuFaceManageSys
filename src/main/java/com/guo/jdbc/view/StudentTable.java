package com.guo.jdbc.view;

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




