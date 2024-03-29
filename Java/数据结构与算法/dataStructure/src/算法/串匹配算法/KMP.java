package 算法.串匹配算法;

/**
 * @title: KMP
 * @Author zxwyhzy
 * @Date: 2023/7/27 10:30
 * @Version 1.0
 * <p>
 * 匹配过程：
 * KMP算法在BF算法的基础上进行改进，通过利用模式字符串自身的信息来避免不必要的字符比较。
 * 首先，KMP算法通过预处理模式字符串，构建部分匹配表（partial match table），
 * 该表记录了模式字符串中的前缀和后缀的最长共有长度。
 * 然后在匹配过程中，如果遇到不匹配的字符，根据部分匹配表中的信息，
 * 移动模式字符串的指针，而无需重新从头开始比较。
 * 时间复杂度：
 * KMP算法的时间复杂度为O(m+n)，
 * 其中m是文本字符串的长度，n是模式字符串的长度。
 * 在KMP算法中，预处理部分匹配表的时间复杂度为O(n)，
 * 而匹配过程中的字符比较次数为O(m+n)，
 * 因此总体时间复杂度为O(m+n)。
 */
public class KMP {

    public static void main(String[] args) {
        String str = "aabaaabaaac";
        String s =   "aabaaac";
        System.out.println(kmpSearch(str, s));
    }


    // KMP算法匹配过程
    public static int kmpSearch(String str, String s) {
        int[] next = buildPartialMatchTable(s);
        char[] strs = str.toCharArray();
        char[] ss = s.toCharArray();
        int i = 0,j = 0;


        while (i < strs.length && j < ss.length){
            if (strs[i] == ss[j]){
                i++;
                j++;
                if (j == ss.length) return i-j;
            }else {
                if (j > 0) {
                    j = next[j - 1];
                } else {
                    //
                    i++;
                }
            }
        }
        return -1;

    }
    // 构建部分匹配表（Partial Match Table）
    public static int[] buildPartialMatchTable(String s) {
        char[] strs = s.toCharArray();
        int[] next = new int[strs.length];
        int k = 0;
        for (int i = 1; i < strs.length; i++) {
            // 修改2 简单
            // 回溯到上一个相同前缀的位置
            while (k > 0 && strs[k] != strs[i]){
                k = next[k-1];
            }
            if (strs[k] == strs[i]){
                k++;
            }
            // 前j个字符，前后缀重复k个字符
            next[i] = k;



            /*if (strs[k] == strs[i]){
                k++;
            }else {
            // 这里有问题：回溯到上一个相同前缀位置时还没有比较就给next[i]赋值且i加1了
                // 回溯到上一个相同前缀的位置
                while (k > 0 && strs[k] != strs[i]){
                    k = next[k-1];
                }
            }
            next[i] = k;*/

            // 修改1 没有问题但有点复杂

            /*if (strs[k] == strs[i]){
                k++;
                next[i] = k; // 更新部分匹配值数组
            } else {
                // 回溯到上一个相同前缀的位置
                while (k > 0 && strs[k] != strs[i]){
                    k = next[k-1];
                }

                if (strs[k] == strs[i]) {
                    k++;
                }
                next[i] = k; // 更新部分匹配值数组
            }*/


        }
        return next;
    }
}
