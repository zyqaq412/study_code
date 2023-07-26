package 算法.递归与分治;

/**
 * @title: Hanoi 汉诺塔问题求解
 * @Author zxwyhzy
 * @Date: 2023/7/26 14:29
 * @Version 1.0
 */

/*
    汉诺塔问题
    汉诺塔问题是一个经典的递归问题，在这个问题中，有三根柱子（一般称为 x、y、z），
    开始时有 n 个盘子按照从大到小的顺序堆叠在柱子 x 上，目标是将这些盘子全部移动到柱子 z 上，
    并且在移动过程中要遵守以下规则：
        1.每次只能移动一个盘子；
        2.大盘子不能放在小盘子上面。

 */
public class Hanoi {


    public static void main(String[] args) {
        move(4, 'x', 'y', 'z');
    }

    /**
     * 当 n 等于 1 时，只有一个盘子，直接将它从 x 移动到 z。
     * 否则，递归地将前 n-1 个盘子从 x 移动到 y，
     * 然后将第 n 个盘子从 x 移动到 z，
     * 最后递归地将前 n-1 个盘子从 y 移动到 z。
     * @param n 盘子数
     * @param x 。
     * @param y 。
     * @param z 。
     */
    public static void move(int n, char x, char y, char z) {
        if (n == 1) {
            // 只有一个的时候 将盘子从x移到z
            System.out.printf("%c -> %c\n", x, z);
        } else {
            move(n - 1, x, z, y); // 将 n-1个盘子从x借助z一道y上
            System.out.printf("%c -> %c\n", x, z); // 将 第n个盘子从x移到z (第n个也是最下面最大的那个)
            move(n - 1, y, x, z); // 将 n-1个盘子从y借助x一道z上
        }
    }

}
