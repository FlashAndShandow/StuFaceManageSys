package org.function;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        openItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Open menu item clicked.");
            }
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

        button.addActionListener(new ActionListener() {
            //执行screenCap的startCapturing方法

            @Override
            public void actionPerformed(ActionEvent e) {
//                JOptionPane.showMessageDialog(null, "Button Clicked!");
                ScreenCapture screenCap = new ScreenCapture();
                screenCap.startApp();
            }
        });

        // 将按钮添加到窗口中
        frame.getContentPane().add(button, BorderLayout.SOUTH);


        // 将菜单栏设置到窗口中
        frame.setJMenuBar(menuBar);

        // 显示窗口
        frame.setVisible(true);
    }
}

class ScreenCapture extends JFrame {
    private static final int SCREENSHOT_INTERVAL = 10000; // 10 秒
    private static final String SCREENSHOT_DIR = "screenshots"; // 截图保存目录
    private static final String SCREENSHOT_FORMAT = "png"; // 截图保存格式
    private static final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private JButton startButton; // 开始截图按钮
    private JButton stopButton; // 停止截图按钮
    private volatile boolean isCapturing = false; // 是否正在截图

    public ScreenCapture() {
        setTitle("屏幕截图应用"); // 设置窗口标题
        setSize(300, 200); // 设置窗口大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置关闭操作
        initUI(); // 初始化用户界面
    }

    private void initUI() {
        // 初始化用户界面组件
        startButton = new JButton("开始截图");
        startButton.addActionListener(e -> startCapturing()); // 添加开始截图按钮事件监听器
        stopButton = new JButton("停止截图");
        stopButton.addActionListener(e -> stopCapturing()); // 添加停止截图按钮事件监听器
        stopButton.setEnabled(false); // 初始状态下停止截图按钮不可用

        // 当用户点击开始按钮时，屏幕截图任务将启动。
        add(startButton, BorderLayout.CENTER);
        add(stopButton, BorderLayout.SOUTH);

    }

    private void startCapturing() {
        isCapturing = true;
        startButton.setEnabled(false); // 启动后禁用开始按钮
        stopButton.setEnabled(true); // 启动后启用停止按钮

        scheduler.scheduleAtFixedRate(() -> {
            if (isCapturing) {
                captureScreen();
                // 注意，我们不在captureScreen内部显示对话框
            }
        }, 0, SCREENSHOT_INTERVAL, TimeUnit.MILLISECONDS);

    }

    private void captureScreen() {
        try {
            Robot robot = new Robot();
            Rectangle screenSize = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenCapture = robot.createScreenCapture(screenSize);

            // 保存截图
            saveScreenshot(screenCapture);

            // 立即显示保存成功的消息，但不阻塞截图过程
//            SwingUtilities.invokeLater(() -> {
//                JOptionPane.showMessageDialog(this, "截图已保存，请查看截图文件夹", "截图完成", JOptionPane.INFORMATION_MESSAGE);
//            });
        } catch (AWTException e) {
            JOptionPane.showMessageDialog(this, "捕获屏幕时出错：" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveScreenshot(BufferedImage image) {
        try {
            int count = 0;
            File screenshotFile;
            do {
                screenshotFile = new File(SCREENSHOT_DIR, "screenshot_" + count + "." + SCREENSHOT_FORMAT);
                count++;
            } while (screenshotFile.exists());

            ImageIO.write(image, SCREENSHOT_FORMAT, screenshotFile);
            JOptionPane.showMessageDialog(this, "截图已保存：" + screenshotFile.getAbsolutePath());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "保存截图时出错：" + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void stopCapturing() {
        if (!isCapturing) return;

        scheduler.shutdown();
        isCapturing = false;
        startButton.setEnabled(true); // 停止后启用开始按钮
        stopButton.setEnabled(false); // 停止后禁用停止按钮
    }

    public void startApp() {

        // 确保截图目录存在
        File dir = new File(SCREENSHOT_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }


        // 创建并显示应用窗口
        SwingUtilities.invokeLater(() -> {
            this.setVisible(true);
            startButton.setEnabled(true); // 启用开始按钮
        });
    }
}
