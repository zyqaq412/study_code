import java.util.Arrays;

/**
 * @title: no_8
 * @Author zxwyhzy
 * @Date: 2023/5/21 21:01
 * @Version 1.0
 * 返回象棋从一个位置到另一个位置 跳法的个数
 */
public class no_8 {
    static int xx = 10;
    static int yy = 9;

    public static void main(String[] args) {
        no_8 n = new no_8();
        long b1 = System.currentTimeMillis();
        System.out.println(n.t1(7, 7, 10));
        long e1 = System.currentTimeMillis();
        System.out.println("耗时:\t"+(e1-b1));
        long b2 = System.currentTimeMillis();
        System.out.println(n.t2(7, 7, 10));
        long e2 = System.currentTimeMillis();
        System.out.println("耗时:\t"+(e2-b2));
        long b3 = System.currentTimeMillis();
        System.out.println(n.dp(7, 7, 10));
        long e3 = System.currentTimeMillis();
        System.out.println("耗时:\t"+(e3-b3));

    }


    // 当前位置(dx,dy) 要去的位置(x,y) 剩余步数(p)
    public int t1(int x, int y, int p) {
        return process1(0, 0, p, x, y);
    }

    public int process1(int dx, int dy, int p, int x, int y) {
        if (!(dx >= 0 && dx < xx && dy >= 0 && dy < yy))
            return 0;
        if (p == 0) {
            return (dx == x && dy == y) ? 1 : 0;
        }
        if (p == 1 && dx == x && dy == y)
            return 0;
        // 象棋格子 xx yy
        // 左上
        int p1 = process1(dx - 1, dy + 2, p - 1, x, y);
        // 右上
        int p2 = process1(dx + 1, dy + 2, p - 1, x, y);
        // 左下
        int p3 = process1(dx - 1, dy - 2, p - 1, x, y);
        // 右下
        int p4 = process1(dx + 1, dy - 2, p - 1, x, y);
        // 左中上
        int p5 = process1(dx - 2, dy + 1, p - 1, x, y);
        // 左中下
        int p6 = process1(dx - 2, dy - 1, p - 1, x, y);
        // 右中上
        int p7 = process1(dx + 2, dy + 1, p - 1, x, y);
        // 右中下
        int p8 = process1(dx + 2, dy - 1, p - 1, x, y);
        return p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8;
    }
    public int t2(int x, int y, int p) {
        int[][][] dp = new int[xx][yy][p+1];
        for (int i = 0;i<xx;i++){
            for (int j = 0;j<yy;j++){
                Arrays.fill(dp[i][j], -1);
            }
        }
        return process2(0, 0, p, x, y,dp);
    }

    // 记忆化搜索
    public int process2(int dx, int dy, int p, int x, int y,int[][][] dp) {
        if (!(dx >= 0 && dx < xx && dy >= 0 && dy < yy))
            return 0;
        if (dp[dx][dy][p]!=-1)
            return dp[dx][dy][p];
        if (p == 0) {
            return (dx == x && dy == y) ? 1 : 0;
        }
        if (p == 1 && dx == x && dy == y)
            return 0;
        // 象棋格子 xx yy
        // 左上
        int p1 = process2(dx - 1, dy + 2, p - 1, x, y,dp);
        // 右上
        int p2 = process2(dx + 1, dy + 2, p - 1, x, y,dp);
        // 左下
        int p3 = process2(dx - 1, dy - 2, p - 1, x, y,dp);
        // 右下
        int p4 = process2(dx + 1, dy - 2, p - 1, x, y,dp);
        // 左中上
        int p5 = process2(dx - 2, dy + 1, p - 1, x, y,dp);
        // 左中下
        int p6 = process2(dx - 2, dy - 1, p - 1, x, y,dp);
        // 右中上
        int p7 = process2(dx + 2, dy + 1, p - 1, x, y,dp);
        // 右中下
        int p8 = process2(dx + 2, dy - 1, p - 1, x, y,dp);
        dp[dx][dy][p] = p1 + p2 + p3 + p4 + p5 + p6 + p7 + p8;
        return dp[dx][dy][p];
    }
    // 动态规划
    public int dp(int x, int y, int p){
        int[][][] dp=new int[xx][yy][p+1];

        dp[x][y][0] = 1;
        for (int rest=1;rest<=p;rest++){
            for (int i=0;i<xx;i++){
                for (int j=0;j<yy;j++){
                    int p1 = pick(dp,i - 1, j + 2, rest - 1);
                    // 右上
                    p1+= pick(dp,i + 1, j + 2, rest - 1);
                    // 左下
                    p1+= pick(dp,i - 1, j - 2, rest - 1);
                    // 右下
                    p1+= pick(dp,i + 1, j - 2, rest - 1);
                    // 左中上
                    p1+= pick(dp,i - 2, j + 1, rest - 1);
                    // 左中下
                    p1+= pick(dp,i - 2, j - 1, rest - 1);
                    // 右中上
                    p1+= pick(dp,i + 2, j + 1, rest - 1);
                    // 右中下
                    p1+= pick(dp,i + 2, j - 1, rest - 1);
                    dp[i][j][rest] = p1;
                }
            }
        }
        return dp[0][0][p];
    }
    public int pick(int[][][] dp,int x,int y,int p){
        if (x<0||x>xx-1||y<0||y>yy-1)
            return 0;
        return dp[x][y][p];
    }
}
