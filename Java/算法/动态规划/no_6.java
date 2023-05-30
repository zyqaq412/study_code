/**
 * @title: no_6
 * @Author zxwyhzy
 * @Date: 2023/5/8 11:43
 * @Version 1.0
 * 最长公共子序列
 * a12bc345def
 * mnp123qrs45z
 * 12345
 */
public class no_6 {

    public static void main(String[] args) {
        String s1 = "ab";
        String s2 ="abc";
        System.out.println(longestCommonSubsequence1(s1,s2));
        System.out.println(longestCommonSubsequence2(s1,s2));
    }
    public static int longestCommonSubsequence1(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        return process1(str1, str2, str1.length - 1, str2.length - 1);
        // 尝试
    }

    // str1[0...i]与str2[0...j]最长公共子序列多长
    public static int process1(char[] str1, char[] str2, int i, int j) {
        if (i == 0 && j == 0) return str1[i] == str2[j] ? 1 : 0;
        else if (i == 0) {
            // i = 0 时 str1只有一个字符 相等就返回1
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                // 不等就不要j了 判断 str2[0...j-1] 与str1[i] 最长公共子序列
               return process1(str1, str2, i, j - 1);
            }
        }else if (j==0){
            if (str1[i] == str2[j]) {
                return 1;
            } else {
               return process1(str1, str2, i-1, j);
            }
        }else {
            int p1 = process1(str1,str2,i-1,j);
            int p2 = process1(str1,str2,i,j-1);
            int p3 =str1[i]==str2[j] ? (1+process1(str1,str2,i-1,j-1)):0;
            return Math.max(p1,Math.max(p2,p3));
        }
    }

    // 根据递归改
    public static int longestCommonSubsequence2(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
            return 0;
        }
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        int N = str1.length;
        int M =str2.length;
        int[][]dp=new int[N][M];
        // if (i == 0 && j == 0) return str1[i] == str2[j] ? 1 : 0;
        dp[0][0] = str1[0]==str2[0]?1:0;
        // 第0行
        /*
        * else if (i == 0) {
            // i = 0 时 str1只有一个字符 相等就返回1
            if (str1[i] == str2[j]) {
                return 1;
            } else {
                // 不等就不要j了 判断 str2[0...j-1] 与str1[i] 最长公共子序列
               return process1(str1, str2, i, j - 1);
            }
        * */
        for(int j = 1;j<M;j++){
            dp[0][j] = str1[0] == str2[j] ? 1:dp[0][j-1];
        }
        // 第0列
        for(int i = 1;i<N;i++){
            dp[i][0] = str1[i] == str2[0] ? 1:dp[i-1][0];
        }
        for (int i=1;i<N;i++){
            for (int j = 1;j<M;j++){
                int p1 = dp[i-1][j];
                int p2 = dp[i][j-1];
                int p3 =str1[i]==str2[j] ? (1+dp[i-1][j-1]):0;
                dp[i][j] = Math.max(p1,Math.max(p2,p3));
            }
        }
        return dp[s1.length()-1][s2.length()-1];
    }
}
