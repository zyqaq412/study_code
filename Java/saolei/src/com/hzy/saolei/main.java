package com.hzy.saolei;

import java.util.Scanner;

import static com.hzy.saolei.game.*;

/**
 * @title: main
 * @Author zxwyhzy
 * @Date: 2023/4/11 12:10
 * @Version 1.0
 */
public class main {
    Scanner sc = new Scanner(System.in);

    public static int COUNT = 0;
    public static void main(String[] args) {

        main main = new main();
        main.menu();
    }

    public void game() {
        System.out.println("游戏开始");
        char[][] show = new char[12][12];
        char[][] lei = new char[12][12];
        creat(show,9,9,'*');
        creat(lei,9,9,'0');
        setLei(lei);
        while (true){
            print(show);
            print(lei);
            System.out.println("请输入排雷坐标");
            int i = sc.nextInt();
            int j = sc.nextInt();
            if (i<1||i>9||j<1||j>9){
                System.out.println("输入有误重新输入");
                continue;
            }
            if (show[i][j] != '*'){
                System.out.println("已经排查过");
                continue;
            }
            paiLei(show,lei,i,j);
            if (COUNT==(9*9-10)){
                System.out.println("成功排雷，游戏结束");
                return;
            }
        }



    }

    public void menu() {

        System.out.println("------------1.play-----------");
        System.out.println("------------0.exit-----------");
        int key = sc.nextInt();

        switch (key) {
            case 1:
                game();
                break;
            case 0:
                break;
            default:
                System.out.println("输入错误请程序输入:");
                menu();
        }

    }
}
