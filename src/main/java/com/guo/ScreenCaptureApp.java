package com.guo;

import com.guo.function.ScreenCapture;

import javax.swing.*;
import java.awt.*;

public class ScreenCaptureApp {
    public static void main(String[] args) {
        // 创建窗口
        JFrame frame = new JFrame("Menu Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // 创建菜单栏
        JMenuBar menuBar = new JMenuBar();

        // 创建菜单
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        // 创建菜单项
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem pasteItem = new JMenuItem("Paste");

        // 为菜单项添加事件监听器
        openItem.addActionListener(e ->  {
                System.out.println("Open menu item clicked.");
        });

        // 将菜单项添加到菜单中
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);

        // 将菜单添加到菜单栏中
        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        // 创建按钮
        JButton button = new JButton("开始屏幕截图");

        button.addActionListener(e -> {
//                JOptionPane.showMessageDialog(null, "Button Clicked!");
                ScreenCapture screenCap = new ScreenCapture();
                screenCap.startApp();
        });

        // 将按钮添加到窗口中
        frame.getContentPane().add(button, BorderLayout.SOUTH);


        // 将菜单栏设置到窗口中
        frame.setJMenuBar(menuBar);

        // 显示窗口
        frame.setVisible(true);
    }
}


