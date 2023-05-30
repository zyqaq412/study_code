package com.hzy.saoleGUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;


/**
 * @title: Main
 * @Author zxwyhzy
 * @Date: 2023/5/28 21:42
 * @Version 1.0
 */
public class Main {
    private JFrame frame;
    private JButton[][] buttons;

    // 记录位置状态 0 未翻开， 1 翻开 ，2 标记
    private int[][] show;
    // 记录 雷的位置 1是雷 0不是雷
    private int[][] lei;
    // 翻开位置个数
    private static int COUNT = 0;
    // 棋盘大小
    private static  int SIZE = 0;
    // 地雷数量
    private static int LEI_SIZE = 0;

    public static void main(String[] args) {
        Main main = new Main();
        SwingUtilities.invokeLater(()-> main.level(main));
    }

    private void level(Main main){
        frame = new JFrame("难度选择");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));

        JButton button1 = new JButton("初级");
        button1.addActionListener(e->{
            SIZE = 8;
            LEI_SIZE=10;
            frame.setVisible(false);
            SwingUtilities.invokeLater(main::createGameGUI);
        });
        panel.add(button1);

        JButton button2 = new JButton("中级");
        button2.addActionListener(e->{
            SIZE = 16;
            LEI_SIZE=35;
            frame.setVisible(false);
            SwingUtilities.invokeLater(main::createGameGUI);
        });
        panel.add(button2);

        JButton button3 = new JButton("高级");
        button3.addActionListener(e->{
            SIZE = 30;
            LEI_SIZE=70;
            frame.setVisible(false);
            SwingUtilities.invokeLater(main::createGameGUI);
        });
        panel.add(button3);

        frame.getContentPane().add(panel);
        frame.setSize(300, 100);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private void createGameGUI() {
        frame = new JFrame("扫雷游戏");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(SIZE, SIZE));
        buttons = new JButton[SIZE][SIZE];
        show = new int[SIZE+2][SIZE+2];
        lei = new int[SIZE+2][SIZE+2];
        COUNT = 0;
        // 初始化棋盘
        for (int i = 1; i <= SIZE; i++) {
            for (int j = 1; j <= SIZE; j++) {
                buttons[i - 1][j - 1] = new JButton();
                buttons[i - 1][j - 1].addMouseListener(new ButtonListener(i, j));
                panel.add(buttons[i - 1][j - 1]);
            }
        }
        gameInit();
        frame.getContentPane().add(panel);
        frame.setSize(500+SIZE*22, 500+SIZE*22);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    private void resetGame() {
        frame.setVisible(false);
        SwingUtilities.invokeLater(() -> level(this));
    }
    private void gameInit() {
        Random random = new Random();
        int count = 0;
        while (count < LEI_SIZE) {
            // 随机选择位置布雷
            int i = random.nextInt(1000) % SIZE + 1;
            int j = random.nextInt(1000) % SIZE + 1;
            if (lei[i][j] != 1) {
                lei[i][j] = 1;
                count++;
            }
        }
    }

    // 这里继承MouseAdapter而不是实现MouseListener
    // MouseAdapter 类是 MouseListener 接口的适配器类，
    // 它提供了对 MouseListener 接口的默认实现，可以选择性地重写需要的方法，而无需实现所有方法。
    private class ButtonListener extends MouseAdapter {
        private final int row;
        private final int col;

        public ButtonListener(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (SwingUtilities.isRightMouseButton(e)) {  // 判断是否是鼠标右键点击
                if (show[row][col] == 0) {
                    show[row][col] = 2;  // 使用 'F' 表示标记
                    buttons[row - 1][col - 1].setText("F");  // 设置按钮文本为 'F'
                } else if (show[row][col] == 2) {
                    show[row][col] = 0;  // 取消标记
                    buttons[row - 1][col - 1].setText("");  // 清空按钮文本
                }
            } else {  // 非右键点击，执行原有的翻开逻辑
                if (show[row][col] == 0) {
                    paiLei(row, col);
                }
            }
        }

        private void paiLei(int row,int col){
            if (lei[row][col] == 1){
                JOptionPane.showMessageDialog(frame, "踩到地雷，游戏结束");
                resetGame();
            }
            zhanKai(row, col);
        }
        private void zhanKai(int row,int col){
            if (lei[row][col] == 1 || row < 1 || row > SIZE || col < 1 || col > SIZE) return;
            // 标记该位置为翻开状态
            show[row][col] = 1;
            // 禁用按钮，防止再次点击
            buttons[row - 1][col - 1].setEnabled(false);
            int count = getLeiCount(row, col);
            if (count == 0) {

                // 展开该位置 周围一圈的位置
                for (int i = row - 1; i <= row + 1; i++) {
                    for (int j = col - 1; j <= col + 1; j++) {
                        if (show[i][j] == 0) {
                            zhanKai(i, j);
                        }
                    }
                }
            } else {
                buttons[row - 1][col - 1].setText(String.valueOf(count));
            }
            COUNT++;
            System.out.println(COUNT);
            if (COUNT == (SIZE * SIZE - LEI_SIZE)) {
                JOptionPane.showMessageDialog(frame, "成功排雷，游戏结束");
                resetGame();
            }
        }
        private int getLeiCount(int row, int col) {
            int count = 0;
            if (lei[row - 1][col - 1] == 1) count++;
            if (lei[row - 1][col] == 1) count++;
            if (lei[row - 1][col + 1] == 1) count++;
            if (lei[row][col - 1] == 1) count++;
            if (lei[row][col + 1] == 1) count++;
            if (lei[row + 1][col - 1] == 1) count++;
            if (lei[row + 1][col] == 1) count++;
            if (lei[row + 1][col + 1] == 1) count++;
            return count;
        }
    }
}
