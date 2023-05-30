package com.hzy.saolei;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
/**
 * @title: guiMain
 * @Author zxwyhzy
 * @Date: 2023/5/28 18:45
 * @Version 1.0
 */
public class guiMain {
    private JFrame frame;
    private JButton[][] buttons;
    private char[][] show;
    private char[][] lei;
    private static int LEI_COUNT = 0;

    private static  int size = 0;
    private static int size_lei = 0;


    public static void main(String[] args) {
        guiMain main = new guiMain();
        SwingUtilities.invokeLater(() -> main.level(main));
    }
    private void level(guiMain main){
        frame = new JFrame("难度选择");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));

        JButton button1 = new JButton("初级");
        button1.addActionListener(e->{
            size = 8;
            size_lei = 10;
            frame.setVisible(false);
            SwingUtilities.invokeLater(main::createAndShowGUI);
        });
        panel.add(button1);

        JButton button2 = new JButton("中级");
        button2.addActionListener(e->{
            size = 16;
            size_lei = 35;
            frame.setVisible(false);
            SwingUtilities.invokeLater(main::createAndShowGUI);
        });
        panel.add(button2);

        JButton button3 = new JButton("高级");
        button3.addActionListener(e->{
            size = 25;
            size_lei = 70;
            frame.setVisible(false);
            SwingUtilities.invokeLater(main::createAndShowGUI);
        });
        panel.add(button3);

        frame.getContentPane().add(panel);
        frame.setSize(300, 100);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void createAndShowGUI() {
        frame = new JFrame("扫雷游戏");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(size, size));
        buttons = new JButton[size][size];
        show = new char[size+2][size+2];
        lei = new char[size+2][size+2];
        // 初始化棋盘
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                buttons[i - 1][j - 1] = new JButton();
                buttons[i - 1][j - 1].addMouseListener(new ButtonListener(i, j));
                panel.add(buttons[i - 1][j - 1]);
                show[i][j] = '*';
                lei[i][j] = '0';
            }
        }

        setLei();

        frame.getContentPane().add(panel);
        frame.setSize(500+size*22, 500+size*22);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    private void resetGame() {
        frame.setVisible(false);
        SwingUtilities.invokeLater(() -> level(this));
    }

    private void setLei() {
        Random random = new Random();
        int count = 0;
        while (count < size_lei) {
            int i = random.nextInt(1000) % size + 1;
            int j = random.nextInt(1000) % size + 1;
            if (lei[i][j] != '1') {
                lei[i][j] = '1';
                count++;
            }
        }
    }

    private void paiLei(int row, int col) {
        if (lei[row][col] == '1') {
            JOptionPane.showMessageDialog(frame, "踩到地雷，游戏结束");
            resetGame();
        }
        zhanKai(row, col);
    }

    private int getLeiCount(int row, int col) {
        int count = 0;
        if (lei[row - 1][col - 1] == '1') count++;
        if (lei[row - 1][col] == '1') count++;
        if (lei[row - 1][col + 1] == '1') count++;
        if (lei[row][col - 1] == '1') count++;
        if (lei[row][col + 1] == '1') count++;
        if (lei[row + 1][col - 1] == '1') count++;
        if (lei[row + 1][col] == '1') count++;
        if (lei[row + 1][col + 1] == '1') count++;
        return count;
    }

    private void zhanKai(int row, int col) {
        if (lei[row][col] == '1' || row < 1 || row > size || col < 1 || col > size) return;
        int count = getLeiCount(row, col);
        if (count == 0) {
            show[row][col] = ' ';
           // buttons[row - 1][col - 1].setBackground(Color.LIGHT_GRAY);  // 设置按钮背景色为浅灰色
            buttons[row - 1][col - 1].setEnabled(false);  // 禁用按钮，防止再次点击
            for (int i = row - 1; i <= row + 1; i++) {
                for (int j = col - 1; j <= col + 1; j++) {
                    if (show[i][j] == '*') {
                        zhanKai(i, j);
                    }
                }
            }
        } else {
            buttons[row - 1][col - 1].setText(String.valueOf(show[row][col]));
            buttons[row - 1][col - 1].setEnabled(false);  // 禁用按钮，防止再次点击
            // 设置不同数字的颜色
            if (count == 1) {
                buttons[row - 1][col - 1].setForeground(Color.BLUE);
            } else if (count == 2) {
                buttons[row - 1][col - 1].setForeground(Color.GREEN);
            } else if (count == 3) {
                buttons[row - 1][col - 1].setForeground(Color.ORANGE);
            } else {
                buttons[row - 1][col - 1].setForeground(Color.RED);
            }

            buttons[row - 1][col - 1].repaint();
        }
        LEI_COUNT++;
        System.out.println("count"+LEI_COUNT);
        if (LEI_COUNT == (size * size - size_lei)) {
            JOptionPane.showMessageDialog(frame, "成功排雷，游戏结束");
            resetGame();
        }
    }

    private class ButtonListener extends MouseAdapter {
        private final int row;
        private final int col;

        public ButtonListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        // mouseClicked -> mousePressed(在鼠标按下时立即响应，从而提高对鼠标右键点击的灵敏度)
        @Override
        public void mousePressed(MouseEvent e) {
            if (SwingUtilities.isRightMouseButton(e)) {  // 判断是否是鼠标右键点击
                if (show[row][col] == '*') {
                    show[row][col] = 'F';  // 使用 'F' 表示标记
                    buttons[row - 1][col - 1].setText("F");  // 设置按钮文本为 'F'
                } else if (show[row][col] == 'F') {
                    show[row][col] = '*';  // 取消标记
                    buttons[row - 1][col - 1].setText("");  // 清空按钮文本
                }
            } else {  // 非右键点击，执行原有的翻开逻辑
                if (show[row][col] == '*') {
                    paiLei(row, col);
                }
            }
        }
    }

}
