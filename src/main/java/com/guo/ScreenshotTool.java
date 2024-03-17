package com.guo;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ScreenshotTool extends JFrame {
    private Point startPoint = null;
    private Point endPoint = null;
    private BufferedImage screenCapture = null;
    private boolean isDragging = false;

    public ScreenshotTool() {
        setUndecorated(true);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setOpacity(0.5f);
        captureFullScreen();

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (startPoint != null && endPoint != null) {
                    int x = Math.min(startPoint.x, endPoint.x);
                    int y = Math.min(startPoint.y, endPoint.y);
                    int width = Math.abs(startPoint.x - endPoint.x);
                    int height = Math.abs(startPoint.y - endPoint.y);
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setColor(new Color(0, 128, 0));
                    g2.setStroke(new BasicStroke(3));
                    g2.drawRect(x, y, width, height);
                }
            }
        };
        setContentPane(panel);

        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                startPoint = e.getPoint();
                isDragging = true;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (isDragging) {
                    endPoint = e.getPoint();
                    isDragging = false;
                    takeScreenshot();
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                endPoint = e.getPoint();
                repaint();
            }
        };

        panel.addMouseListener(mouseAdapter);
        panel.addMouseMotionListener(mouseAdapter);
    }

    private void captureFullScreen() {
        try {
            Robot robot = new Robot();
            Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            screenCapture = robot.createScreenCapture(screenRect);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private void takeScreenshot() {
        if (startPoint != null && endPoint != null) {
            int x = Math.min(startPoint.x, endPoint.x);
            int y = Math.min(startPoint.y, endPoint.y);
            int width = Math.abs(startPoint.x - endPoint.x);
            int height = Math.abs(startPoint.y - endPoint.y);

            BufferedImage screenshot = screenCapture.getSubimage(x, y, width, height);
            saveScreenshot(screenshot);
            dispose();
        }
    }

    private void saveScreenshot(BufferedImage screenshot) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("保存截图");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("PNG Image", "png"));

        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            if (!file.getName().toLowerCase().endsWith(".png")) {
                file = new File(file.getParentFile(), file.getName() + ".png");
            }

            if (file.exists()) {
                int result = JOptionPane.showConfirmDialog(this, "文件已经存在, 是否确认覆盖?", "文件已存在", JOptionPane.YES_NO_OPTION);
                if (result != JOptionPane.YES_OPTION) {
                    return; // Do not overwrite the file
                }
            }

            try {
                ImageIO.write(screenshot, "PNG", file);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving screenshot: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            ScreenshotTool tool = new ScreenshotTool();
            tool.setVisible(true);
        });
    }
}
