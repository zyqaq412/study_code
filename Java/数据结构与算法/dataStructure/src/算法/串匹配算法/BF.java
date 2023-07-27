package 算法.串匹配算法;

/**
 * @title: BF 暴力匹配算法
 * @Author zxwyhzy
 * @Date: 2023/7/27 10:16
 * @Version 1.0
 * 匹配过程：
 *      BF算法采用简单直接的方法，从文本字符串的第一个字符开始，
 *      逐个字符与模式字符串进行比较。如果遇到不匹配的字符，则将文本字符串的指针向后移动一位，
 *      并重新从该位置开始与模式字符串进行比较，直到找到匹配或文本字符串被遍历完。
 * 时间复杂度：
 *      最坏情况下的时间复杂度为O(m*n)，
 *      其中m是文本字符串的长度，n是模式字符串的长度。
 *      因为在最坏情况下，每次遇到不匹配都需要从头开始重新比较。
 *
 */
public class BF {


    public static void main(String[] args) {
        String str = "iloveyou";
        String s = "live";
        System.out.println(process(str,s));
    }
    public static boolean process(String str ,String s){
        char[] strs = str.toCharArray();
        char[] ss = s.toCharArray();
        int i = 0,j = 0;
        int z = 0;

        // 子串长度大于剩余未比较主串长度，那s一定不是str的字串
        while (z <= strs.length - ss.length){
            if (strs[i] == ss[j]){
                i++;
                j++;
                if (j == ss.length) return true;
            }else {
                j = 0;
                i = ++z;
            }
        }

        return false;
    }
}
