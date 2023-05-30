/**
 * @title: no_7
 * @Author zxwyhzy
 * @Date: 2023/5/17 19:40
 * @Version 1.0
 * 最长回文子序列
 */
public class no_7 {
    public static void main(String[] args) {
        System.out.println(lpsl("d123s21q"));
        System.out.println(dp("d123s21q"));
    }

    public static int lpsl(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        return f(str, 0, str.length - 1);
    }

    // str[l....r]最长回文子序列
    public static int f(char[] str, int l, int r) {
        // 只剩一个字符了一定是回文
        if (l == r)
            return 1;
        else if (l == r - 1)
            return str[l] == str[r] ? 2 : 1;
        else {
            int n1 = f(str, l + 1, r);
            int n2 = f(str, l, r - 1);
            int n3 = str[l] == str[r] ? f(str, l + 1, r - 1) + 2 : f(str, l + 1, r - 1);
            return Math.max(n3, Math.max(n1, n2));
        }
    }

    public static int dp(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int N = str.length;
        int[][] dp = new int[N][N];
        dp[N-1][N-1] = 1;
        for (int i = 0; i < N - 1; i++) {
            dp[i][i] = 1;
            dp[i][i + 1] = str[i] == str[i + 1] ? 2 : 1;
        }
        for (int l = N-3;l>=0;l--){
            for (int r = l+2;r<N;r++){
                int n1 = dp[l][r-1];
                int n2 = dp[l+1][r];
                int n3 = str[r] == str[l] ?( 2 + dp[l + 1][r - 1]) : dp[l + 1][r - 1];
                dp[l][r] = Math.max(n1, Math.max(n2, n3));
            }
        }
        return dp[0][N - 1];
    }
}
