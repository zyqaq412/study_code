package 算法.递归与分治;

/**
 * @title: EightQueens 八皇后问题
 * @Author zxwyhzy
 * @Date: 2023/7/26 14:48
 * @Version 1.0
 */

/*
  八皇后问题
  是由国际西洋棋棋手马克斯·贝瑟尔于1848年提出的问题，是回溯算法的典型案例。
  问题表述为：在8×8格的国际象棋上摆放8个皇后，
  使其不能互相攻击，即任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法。
 */
public class EightQueens {
    static int count  = 0;
    static int[] queens = new int[8];

    public static void main(String[] args) {
        solve(0);
    }

    /**
     *  使用一维数组存放结果 数组下标i表示第i列 arr[i] 表示第arr[i]行
     * @param n 开始索引
     */
    private static void solve(int n) {
        if (n == 8) {
            printQueens();
            return;
        }

        for (int i = 0; i < 8; i++) {
            queens[n] = i;
            if (isValid(n)) {
                solve(n + 1);
            }

        }
    }

    private static boolean isValid(int n) {

        for (int i = 0 ;i < n ;i++){
            // 因为使用一维数组存放结果所以不存在在同一列的情况
            // queens[i] == queens[n] 同一行
            // Math.abs(queens[n] - queens[i])  ==  Math.abs(n - i) 同一对角线
            if (queens[i] == queens[n] || Math.abs(queens[n] - queens[i])  ==  Math.abs(n - i)   ){
                return false;
            }
        }
        return true;
    }

    private static void printQueens() {
        System.out.printf("第%d种摆法\n",++count);
        for (int i : queens){
            System.out.print(i+"\t");
        }
        System.out.println();
    }

}
