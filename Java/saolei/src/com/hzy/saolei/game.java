package com.hzy.saolei;

import java.util.Random;

/**
 * @title: game
 * @Author zxwyhzy
 * @Date: 2023/4/11 12:19
 * @Version 1.0
 */
public class game {
    public static void creat(char[][] arr,int row,int col,char c){
        for (int i = 1; i <= row ; i++) {
            for (int j = 1; j <= col; j++) {
                arr[i][j] = c;
            }
        }
    }

    public static void print(char[][] arr){
        System.out.println("--------------扫雷游戏--------------");
        for (int i = 0; i <= 9; i++) {
            System.out.print(i+"\t");
        }
        System.out.println();
        for (int i = 1; i <= 9 ; i++) {
            System.out.print(i+"\t");
            for (int j = 1; j <= 9; j++) {
                System.out.print(arr[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("--------------扫雷游戏--------------");
    }
    public static void setLei(char[][] arr){
        Random random = new Random();
        int count = 0;
        while (count<10){
            int i = random.nextInt(1000)%9 +1;
            int j = random.nextInt(1000)%9 +1;
            if (arr[i][j] != '1'){
                arr[i][j]  = '1';
                count++;
            }
        }
    }
    public static void paiLei(char[][] show,char[][] lei,int row,int col){
        if (lei[row][col] == '1'){
            System.out.println("踩到地雷游戏结束");
            System.exit(0);
        }
        zhanKai(show,lei,row,col);
    }
    public static int getLeiCount(char[][] lei,int row,int col){
        int count = 0;
        if (lei[row-1][col-1] == '1') count++;
        if (lei[row-1][col] == '1') count++;
        if (lei[row-1][col+1] == '1') count++;
        if (lei[row][col-1] == '1') count++;
        if (lei[row][col+1] == '1') count++;
        if (lei[row+1][col-1] == '1') count++;
        if (lei[row+1][col] == '1') count++;
        if (lei[row+1][col+1] == '1') count++;
        return count;
    }
    public static void zhanKai(char[][] show,char[][] lei,int row,int col){
        if (lei[row][col] == '1' || row<1||row>9||col<1||col>9) return;
        int count = getLeiCount(lei,row,col);
        if (count==0) {
            show[row][col] = '#';
            for (int i = row-1; i <= row+1; i++) {
                for (int j = col-1; j <= col+1 ; j++) {
                    if (show[i][j]== '*'){
                        zhanKai(show,lei,i,j);
                    }

                }
            }
        }
        else {
            show[row][col] = (char)(count+'0');
        }
        main.COUNT++;
    }
}
