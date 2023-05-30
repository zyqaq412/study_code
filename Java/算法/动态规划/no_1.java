public class no_1 {
    /**
     *
     * 假设有排成一行的N个位置，记为1~N,N一定大于或等于2
     * 开始时机器人在其中的M位置上(M一定是1~N中的一个)如果机器人来到1位置，那么下一步只能往右来到2位置﹔如果机器人来到N位置，那么下一步只能往左来到N-1位置;
     * 如果机器人来到中间位置，那么下一步可以往左走或者往右走﹔
     * 规定机器人必须走K步，最终能来到P位置(P也是1~N中的一个)的方法有多少种给定四个参数N、M、K、P，返回方法数。
     */
    public static void main(String[] args) {
        // 很久很久
        /*long start1 = System.currentTimeMillis();
        long c1 = count1(20,4,10,50);
        long end1 = System.currentTimeMillis();*/

        long start2 = System.currentTimeMillis();
        long c2 = count2(20,4,10,4000); // 1毫秒
        long c5 = count2(7,2,4,4); // 1毫秒
        long end2 = System.currentTimeMillis();

        long start3 = System.currentTimeMillis();
        long c3 = count3(20,4,10,4000); // 1毫秒
        long c4 = count3(7,2,4,4); // 1毫秒
        long end3 = System.currentTimeMillis();

       // System.out.println("方法一结果:"+c1+"\t方法一耗时:"+(end1-start1));
        System.out.println("方法二结果:"+c2+"\t方法二耗时:"+(end2-start2));
        System.out.println("方法二结果:"+c5+"\t方法二耗时:"+(end2-start2));
        System.out.println("方法三结果:"+c3+"\t方法二耗时:"+(end3-start3));
        System.out.println("方法三结果:"+c4+"\t方法二耗时:"+(end3-start3));
    }

    public static long count3(int N, int start, int aim, int K){
        // 数组下标 0 开始  位置1开始所以+1防止数组越界
        long[][] dp = new long[N+1][K+1];

        // 步数为0 只有在目标位置才为1
        for (int i = 1; i <= N; i++) {
            if (i == aim) dp[i][0] = 1;
            else dp[i][0] = 0;
        }

        // i步数  j位置
        for (int i = 1; i <= K; i++) {
            // 在1位置时 只能往右走 就相当于  (1,i)位置1，步数i == (2,i-1)位置2，步数i-1
            dp[1][i] = dp[2][i-1];
            for (int j = 1; j < N ; j++) {
                // 在中间的 可以往两边走
                // 往左走 (j,i)位置j，步数i == (j-1,i-1)位置j-1，步数i-1
                // 往右走 (j,i)位置j，步数i == (j+1,i-1)位置j+1，步数i-1
                dp[j][i] = dp[j-1][i-1] + dp[j+1][i-1];
            }
            // 在N位置时 只能往左走 就相当于  (N,i)位置N，步数i == (N-,i-1)位置N-1，步数i-1
            dp[N][i] = dp[N-1][i-1];
        }
        return dp[start][K];
    }


    public static long count2(int N, int start, int aim, int K){
        // 数组下标 0 开始  位置1开始所以+1防止数组越界
        long[][] dp = new long[N+1][K+1];
        for(int i = 0;i<N+1;i++){
            for(int j = 0;j<K+1;j++){
                dp[i][j] = -1;
            }
        }
        dp[aim][0] = 1;
        return find2(start, K, aim, N,dp);
    }
    public static long find2(int cur, int rest, int aim, int N,long[][] dp){
        if(dp[cur][rest]!= -1) return dp[cur][rest];
        if(rest == 0) {
            return dp[cur][rest] = cur == aim?1:0;
        }
        if (cur == 1) {
            return dp[cur][rest] = find2(2, rest - 1, aim, N,dp);
        } else if (cur == N) {
            return dp[cur][rest] = find2(N-1, rest - 1, aim, N,dp);
        } else {
            return dp[cur][rest] = (find2(cur-1, rest - 1, aim, N,dp)+ find2(cur+1, rest - 1, aim, N,dp));
        }
    }

    /**
     *
     * @param N 长度 1-N
     * @param start 开始位置
     * @param aim 目标位置
     * @param K 剩余步数
     * @return
     */
    public static int count1(int N, int start, int aim, int K) {
        return find1(start, K, aim, N);
    }

    /**
     *
     * @param cur 当前位置
     * @param rest 剩余步数
     * @param aim 目标位置
     * @param N 长度
     * @return
     */

    public static int find1(int cur, int rest, int aim, int N) {
        if(rest == 0) {
            // 不能动了 当前位置等于目标位置说明此路线可以
            return cur == aim ? 1 : 0;
        }

        if (cur == 1) {
            return find1(2, rest - 1, aim, N);
        } else if (cur == N) {
            return find1(N-1, rest - 1, aim, N);
        } else {
            return find1(cur-1, rest - 1, aim, N)+ find1(cur+1, rest - 1, aim, N);
        }

    }
}