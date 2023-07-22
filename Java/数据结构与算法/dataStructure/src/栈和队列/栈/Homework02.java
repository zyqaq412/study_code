package 栈和队列.栈;

import java.util.Stack;

/**
 * @title: Homework02 逆波兰表达式 and 中缀表达式 转 逆波兰表达式(后缀表达式)
 * @Author zxwyhzy
 * @Date: 2023/7/22 14:55
 * @Version 1.0
 */
public class Homework02 {

    public static void main(String[] args) {
        // String str = "1 2 - 4 5 + *";
        // System.out.println(t1(str));
        //String s = "(11-2+3)*(4+5)"; // 11 2 - 3 + 4 5 + *
        String s = "1+(2-3)*4+10/5"; // 1 2 3 - 4 * 10 5 / + +
        String s2 = t2(s);
        System.out.printf("中缀表达式%S转为逆波兰表达式: %s",s,s2);
        System.out.println("计算结果: "+ t1(s2));
        System.out.println("计算结果: "+ t1("1 2 3 - 4 * + 10 5 / +"));

        // 1 2 + 3 4 * +
        System.out.printf("中缀表达式%S转为逆波兰表达式: %s","1+2+3*4",t2("1+2+3*4"));


    }
    // region 中缀表达式 转 逆波兰表达式(后缀表达式)

    // (11-2+3)*(4+5)
    public static String t2(String str){
        Stack<Character> stack = new Stack();
        char[] strs = str.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i =0 ; i<strs.length;i++){
            // 数字
            if (strs[i]>=48 && strs[i]<=57){
                if (i+1< strs.length &&strs[i+1]>=48 && strs[i+1]<=57){
                    sb.append(strs[i]);
                }else {
                    sb.append(strs[i]).append(" ");
                }


            // 符号
            }else {
                if (stack.isEmpty()){
                    stack.push(strs[i]);
                }else {

                    if (strs[i] == '('){
                        stack.push(strs[i]);
                    }else if (strs[i] == ')'){
                       char temp = stack.pop();
                       while (temp != '('){
                           sb.append(temp).append(" ");
                           temp = stack.pop();
                       }
                       // + - * /
                    }else {
                        char temp = stack.peek();
                        // 如果栈顶是计算符号则比较优先级
                        /*if (temp != '('){
                        // 栈顶优先级大于等于入栈优先级就弹出
                            if (getIndex(temp)>=getIndex(strs[i])){
                                sb.append(stack.pop()).append(" ");
                                stack.push(strs[i]);
                            }else {
                                stack.push(strs[i]);
                            }
                        }else {
                            stack.push(strs[i]);
                        }*/
                        if (temp != '('){
                            // 循环比较
                            while (getIndex(temp)>=getIndex(strs[i])){
                                sb.append(stack.pop()).append(" ");
                                if (stack.isEmpty()){
                                    break;
                                }
                                temp = stack.peek();
                            }
                            stack.push(strs[i]);

                        }else {
                            stack.push(strs[i]);
                        }
                    }
                }
            }

        }
        while (!stack.isEmpty()){
            sb.append(stack.pop()).append(" ");
        }
        return sb.toString();
    }

    // endregion
    // 获取计算符优先级
    public static int getIndex(char c){
        switch (c){
            case '+':
                return 1;
            case '-':
                return 1;
            case '*':
                return 2;
            case '/':
                return 2;
            default:
                return 3;
        }
    }





    // region 根据逆波兰表达式计算结果

    public static int t1(String str){
        String[] strs = str.split(" ");
        Stack<Integer> stack = new Stack();
        int ans ;
        for (String s : strs){
            if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")){
                ans = jis(stack.pop(),stack.pop(),s);
                stack.push(ans);
            }else {
                stack.push(Integer.valueOf(s));
            }
        }
        return stack.pop();

    }
    public static int jis(int i2,int i1,String s){
        if (s.equals("+")){
            return i1+i2;
        }else if (s.equals("-")){
            return i1-i2;
        }else if (s.equals("*")){
            return i1*i2;
        }else {
            return i1/i2;
        }
    }
    // endregion
}
