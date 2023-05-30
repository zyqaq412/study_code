/**
 * @title: no_4
 * @Author zxwyhzy
 * @Date: 2023/4/25 19:58
 * @Version 1.0
 *
 *  数字字符对应
 *  1-26 对应 a-z
 *  一个字符串“111”就可以转换为
 *  ”aaa“，”ka“，”ak“
 *  给定一个数字字符组成的字符串str，返回有多少种转化结果
 */
public class no_4 {

    public static void main(String[] args) {
        int number = number("1123123213221");
        int dp = dp("1123123213221");
        System.out.println(number);
        System.out.println(dp);
    }

    public static int number(String str){
        if (str == null || str.length()==0){
            return 0;
        }
        return process(str.toCharArray(),0);
    }

    private static int process(char[] s, int i) {
        // i==s.length 说明到了数组边界 那这就是一次有效的转换
        if (s.length == i)
            return 1;

        // 0没有对应的字母 不能转换
        if (s[i] == '0')
            return 0;
        // 每个数字一一对应
        int count = process(s,i+1);
        // 两个数字对应一个字母
        if ((i+1)<s.length&&((s[i]-'0')*10+s[i+1]-'0')<27)
            count+=process(s,i+2);
        return count;
    }

    public static int dp(String str){
        if (str == null || str.length()==0)
            return 0;
        char[] s = str.toCharArray();
        int N = s.length;
        // 因为数组要放越界位置所以长度加1
        int[] dp = new int[N+1];
        // 越界位置返回1  if (s.length == i) return 1;
        dp[N] = 1;
        for (int i = N-1; i >= 0; i--) {
            // 数组默认为0 if (s[i] == '0') return 0;
            // 所以只有s[i] != '0' 的时候再去改dp的值
            if (s[i] != '0'){
                // 一个数字对应一个字母
                // int count = process(s,i+1);
              int count  = dp[i+1];
                // 两个数字对应一个字母
                // if ((i+1)<s.length&&((s[i]-'0')*10+s[i+1]-'0')<27) count+=process(s,i+2);
                if ((i+1)<N&&((s[i]-'0')*10+s[i+1]-'0')<27)
                    count+=dp[i+2];
                dp[i] = count;
            }
        }
        return dp[0];
    }
}
