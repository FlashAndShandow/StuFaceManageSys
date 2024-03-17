package com.guo.jdbc.view;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.net.URL;
import java.util.Vector;


public class MainView extends JFrame implements ActionListener, FocusListener {
    //创建一个数据库JFrame页面
    //分别创建一个JPanel，用来放置上下两个部分的组件
    JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton addBtn = new JButton("添加");
    JButton deleteBtn = new JButton("删除");
    JButton updateBtn = new JButton("修改");
    JButton searchBtn = new JButton("查询");
    JTextField searchField = new JTextField(20);

    JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JButton preBtn = new JButton("上一页");
    JButton nextBtn = new JButton("下一页");

    StudentTable studentTable = new StudentTable();//创建一个数据表格主体
    public MainView(){
        //创建一个数据库JFrame页面
        super("学籍管理系统");
        setVisible(true);//设置窗体可见
        setLocationRelativeTo(null);//窗口即居中显示，即窗口不相对任何内容
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//点击关闭按钮时触发的事件，EXIT_ON_CLOSE表示直接关闭窗口
        setResizable(true);//设置窗体大小可变
        setSize(800,600);//设置窗体的大小
        setLayout(new BorderLayout());//设置窗体的布局为网格布局


        //设置窗体图标
        URL imgUrl =  getClass().getResource("/UserHomeRes/head.png");
        setIconImage(new ImageIcon(imgUrl).getImage());

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//点击关闭按钮时触发的事件，EXIT_ON_CLOSE表示直接关闭窗口
        /**
         * 也可以自己设置窗口位置来居中
         * Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
         * int offsetx = (screenSize.width - 500) / 2;
         * int offsety = (screenSize.height - 500) / 2;
         * jFrame.setLocation(offsetx, offsety);
         */


//        if (bgUrl != null) {//添加背景图片
//            ImageIcon bg = new ImageIcon(bgUrl);//获取背景图片
//            JLabel label = new JLabel(bg);//将背景图放在标签里。
//            label.setBounds(0, 0, bg.getIconWidth(), bg.getIconHeight());//设置标签的大小位置
//            this.getLayeredPane().add(label, Integer.MIN_VALUE);//将背景标签添加到JFrame的LayeredPane面板里。
//        } else {
//            System.err.println("未找到图片资源: /UserHomeRes/background.png");
//        }

        Container container = getContentPane();//通过getContentPane来获得JFrame子类容器
        NorthLayout(container);
        CenterLayout(container);
        SouthLayout(container);

    }

    public JPanel createHead() {
        JPanel jpl = new JPanel();
        jpl.setLayout(new FlowLayout(FlowLayout.CENTER, 33, 20));

        String tit = "   学籍管理界面";
        JLabel lb0 = new JLabel(tit);                         //标题
        jpl.add(lb0);

//        String by = "   By:郭联鑫";
//        JLabel lb3 = new JLabel(by);                             //by
//        jpl.add(lb3);

        jpl.setOpaque(false);
        return jpl;
    }

    private void NorthLayout(Container contentPane) {
        //对界面上方的布局添加组件
        northPanel.add(addBtn);
        northPanel.add(updateBtn);
        northPanel.add(deleteBtn);
        northPanel.add(searchField);
        northPanel.add(searchBtn);
        contentPane.add(northPanel, BorderLayout.NORTH);
    }

    private void CenterLayout(Container contentPane) {
        //创建三个测试数据
        Vector<Vector<Object>> all_data = new Vector<>();
        Vector<Object> row1 = new Vector<>();
        row1.addElement("1");
        row1.addElement("1");
        row1.addElement("1");
        row1.addElement("1");
        row1.addElement("1");
        row1.addElement("1");


        Vector<Object> row2 = new Vector<>();
        row2.addElement("2");
        row2.addElement("2");
        row2.addElement("2");
        row2.addElement("2");
        row2.addElement("2");
        row2.addElement("2");

        Vector<Object> row3 = new Vector<>();
        row3.addElement("3");
        row3.addElement("3");
        row3.addElement("3");
        row3.addElement("3");
        row3.addElement("3");
        row3.addElement("3");


        all_data.addElement(row1);
        all_data.addElement(row2);
        all_data.addElement(row3);
        StudentTableModel stm = StudentTableModel.assembleModel(all_data);
        studentTable.setModel(stm);
        studentTable.renderRule();

        //将表格添加到布局的中部
        JScrollPane jsp = new JScrollPane(studentTable);
        contentPane.add(jsp,BorderLayout.CENTER);

    }

    private void SouthLayout(Container contentPane){
        //对界面下方的布局添加组件
        southPanel.add(preBtn);
        southPanel.add(nextBtn);
        contentPane.add(southPanel,BorderLayout.SOUTH);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    public static void main(String[] args) {
        new MainView().setVisible(true);
    }
}





