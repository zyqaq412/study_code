/**
 * @title: no_3
 * @Author zxwyhzy
 * @Date: 2023/4/21 15:16
 * @Version 1.0
 * 背包问题
 * 给定商品价值，重量和背包容量
 * 返回能拿的最大价值
 *
 */
public class no_3 {

    public static void main(String[] args) {
        int[] w = {1,1,3,4,2};
        int[] v = {2,10,100,50,30};
        System.out.println(maxValue(w,v,5));
        System.out.println(dp(w,v,5));
    }

    public static int maxValue(int[] w,int[] v,int bag){
        if (w==null||v==null||w.length==0||v.length==0) return 0;

        return process(w,v,0,bag);
    }

    private static int process(int[] w, int[] v, int index, int rest) {
        // 背包容量为负，说明上一次拿货失败
        if (rest<0) return -1;
        // 没货呢
        if (index == w.length) return 0;

        // 不要当前的货(不加 v[index])  rest 容量不变
        int i = process(w,v,index+1,rest);

        //要当前的货
        // 这么写不管这次拿了还有没有空间 v[index] 都已经拿到了
        //int j =v[index] +  process(w,v,index+1,rest-w[index]);
        int j = 0;
        // 先判断这次拿了是否还有空间
        int next = process(w,v,index+1,rest-w[index]);
        if (next!=-1){
            j = v[index]+next;
        }
        return Math.max(i,j);
    }

    // 动态规划
    public static int dp(int[] w,int[] v,int bag){
        int N = w.length;
        // 代表当 在第N个商品还剩bag容量时的最大价值
        int[][] dp = new int[N+1][bag+1];

        // if (index == w.length) return 0; 可以看出index等于商品个数 商品为空价值为0
        // 所以index从N-1开始
        for (int index = N-1; index >= 0 ; index--) {
            for (int rest = bag; rest > 0 ;rest--) {
                // 动态转移公式可以通过递归代码推出

                // 不拿当前商品
                int i = dp[index+1][rest];

                // 拿当前商品
                int j = 0;
                if (rest-w[index] >= 0){
                    j = v[index]+dp[index+1][rest-w[index]];
                }
                dp[index][rest] = Math.max(i,j);
            }

        }

        return dp[0][bag];
    }

}
