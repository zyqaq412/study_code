package 栈和队列.栈;

import java.util.Scanner;
import java.util.Stack;

/**
 * @title: Homework01 利用栈 完成输入二进制转换为十进制/八进制
 * @Author zxwyhzy
 * @Date: 2023/7/22 14:14
 * @Version 1.0
 */
public class Homework01 {
    public static void main(String[] args) {
        // t1();
        t2();
    }
    // 二转十
    public static void t1(){
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        Stack stack = new Stack<Integer>();
        int l = str.length();
        for (int i = 0 ;i<l;i++){
            stack.push(Integer.valueOf(String.valueOf(str.charAt(i))));
        }
        int ans = 0;
        for (int i = 0 ;i<l;i++){
            int temp = (int) stack.pop();
            ans += temp * Math.pow(2,i);
        }
        System.out.printf("二进制：%S\t转换为十进制= %d",str,ans);
    }

    // 二转八
    public static void t2(){
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        Stack stack = new Stack<Integer>();
        int l = str.length();
        for (int i = 0 ;i<l;i++){
            stack.push(Integer.valueOf(String.valueOf(str.charAt(i))));
        }
        StringBuilder ans = new StringBuilder();
        int temp = 0;
        int count = 0;
        for (int i = 0 ;i<l;i++){
            temp += (int) stack.pop()*Math.pow(2,count);
            if (count == 2){
                count = 0;
                ans.append(temp);
                temp = 0;
            }else {
                count++;
            }

        }
        ans.append(temp);
        ans.reverse();
        System.out.printf("二进制：%S\t转换为八进制= %S",str,ans);
    }
}
