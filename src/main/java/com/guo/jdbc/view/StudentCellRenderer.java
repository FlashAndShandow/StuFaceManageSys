package com.guo.jdbc.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class StudentCellRenderer extends DefaultTableCellRenderer {
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
