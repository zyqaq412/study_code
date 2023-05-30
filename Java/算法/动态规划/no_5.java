import java.util.HashMap;

/**
 * @title: no_5
 * @Author zxwyhzy
 * @Date: 2023/4/26 22:35
 * @Version 1.0
 * 贴纸
 * 给出贴纸和需要的字母
 * 判断最少需要几张贴纸可以拼成需要的字母
 */
public class no_5 {

    public static void main(String[] args) {
        String[] stickers = {"ab","aa","sd"};
        String target = "aaababsdbsd";
        System.out.println(minStickers1(stickers,target));
        System.out.println(minStickers2(stickers,target));
        System.out.println(minStickers3(stickers,target));
    }

    /**
     *
     * @param stickers 贴纸
     * @param target 需要拼凑的字符串
     * @return 至少需要多少张
     */
    public static int minStickers1(String[] stickers,String target){
        int ans = process1(stickers,target);
        return ans==Integer.MAX_VALUE?-1:ans;
    }

    // 过程1
    public static int process1(String[] stickers,String target){
        if (target.length() == 0) return 0;
        int min = Integer.MAX_VALUE;
        for(String first:stickers){
            // 返回减去第一张贴纸后还差的字母
            String rest = minus(target,first);
            // rest.length() == target.length() 说明当前贴纸不包含需要的字母
            if (rest.length() != target.length()){
                min = Math.min(min,process1(stickers,rest));
            }
        }
        // min != Integer.MAX_VALUE 说明成功的剪出了需要的字母
        // 贴纸数量就需要加上当前贴纸
        return min + (min != Integer.MAX_VALUE?1:0);
    }
    public static String minus(String target,String tiezi){
        char[] t1= target.toCharArray();
        char[] t2 = tiezi.toCharArray();
        int[] arr = new int[26]; // 字母表

        // 统计需要的字母
        for(char c:t1){
            arr[c-'a']++;
        }
        // 减去当前贴纸有的字母
        for(char c:t2){
            arr[c-'a']--;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0 ;i<26;i++){
            // arr[i]>0 还差的字母
            if (arr[i] > 0){
                for (int j = 0; j < arr[i]; j++) {
                    sb.append((char)(i+'a'));
                }
            }
        }
        return sb.toString();
    }

    public static int minStickers2(String[] stickers,String target){
        if (target.length() == 0) return 0;
        int N = stickers.length;
        int[][] dp = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] str = stickers[i].toCharArray();
            for (char c:str) {
                dp[i][c-'a']++;
            }
        }
        int ans = process2(dp,target);
        return ans == Integer.MAX_VALUE ? -1:ans;
    }
    /**
     * stickers[i]  i贴纸的字符统计
     * @param stickers 贴纸字符统计
     * @param target 目标
     * @return 需要贴纸
     */
    public static int process2(int[][] stickers,String target){
        if (target.length() == 0 ) return 0;
        char[] t = target.toCharArray();
        int[] tcount = new int[26];
        // 词频统计
        for(char c:t){
            tcount[c-'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            // 第i张贴纸的词频统计
            int[] sticker = stickers[i];
            if (sticker[t[0]-'a'] > 0){
                // 还差的字符
                StringBuffer sb = new StringBuffer();
                for (int j = 0;j<26;j++){
                    if (tcount[j]>0){
                        int nums = tcount[j] - sticker[j];
                        for (int k=0;k<nums;k++){
                            sb.append((char)(j+'a'));
                        }
                    }
                }
                String rest = sb.toString();
                min = Math.min(min,process2(stickers,rest));
            }
        }
        return min+(min!=Integer.MAX_VALUE?1:0);
    }



    public static int minStickers3(String[] stickers,String target){
        if (target.length() == 0) return 0;
        int N = stickers.length;
        int[][] s = new int[N][26];
        for (int i = 0; i < N; i++) {
            char[] str = stickers[i].toCharArray();
            for (char c:str) {
                s[i][c-'a']++;
            }
        }
        HashMap<String,Integer> dp = new HashMap<>();
        // target为空后返回0
        dp.put("",0);
        int ans = process3(s,target,dp);
        return ans == Integer.MAX_VALUE ? -1:ans;
    }

    /**
     * stickers[i]  i贴纸的字符统计
     * @param stickers 贴纸字符统计
     * @param target 目标
     * @param dp 缓存 当前字符串需要的最少贴纸
     * @return 需要贴纸
     *
     * 傻缓存，在process2的基础上添加一个map存放结果，可以去掉重复操作
     *
     */
    public static int process3(int[][] stickers, String target, HashMap<String,Integer> dp){
        if (dp.containsKey(target))
            return dp.get(target);
        char[] t = target.toCharArray();
        int[] tcount = new int[26];
        for(char c:t){
            tcount[c-'a']++;
        }
        int N = stickers.length;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int[] sticker = stickers[i];
            if (sticker[t[0]-'a'] > 0){
                StringBuffer sb = new StringBuffer();
                for (int j = 0;j<26;j++){
                    if (tcount[j]>0){
                        int nums = tcount[j] - sticker[j];
                        for (int k=0;k<nums;k++){
                            sb.append((char)(j+'a'));
                        }
                    }
                }
                String rest = sb.toString();
                min = Math.min(min,process3(stickers,rest,dp));
            }
        }
        int ans = min+(min!=Integer.MAX_VALUE?1:0);
        dp.put(target,ans);
        return ans;
    }



}
