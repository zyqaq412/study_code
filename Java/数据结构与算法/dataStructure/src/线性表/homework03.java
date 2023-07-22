package 线性表;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Random;
import java.util.Scanner;

/**
 * @title: homework03 加密 使用双向链表实现(一般情况用数组更快)
 * @Author zxwyhzy
 * @Date: 2023/7/17 22:43
 * @Version 1.0
 */
public class homework03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入明文: ");
        String input = sc.next();
        // 加密
        // 获取密码本
        ListNodes ln = init();
        // 1.获取密钥
        int[] miyao = miyao(input.length());

        // 2.根据密钥加密
        String miwen = jiami(input, miyao, ln);
        // 密文密钥持久化
        local(miwen,miyao);
        System.out.println("密文: " + miwen);
        String mingwen = jiemi(miwen, miyao, ln);
        System.out.println("解密明文: " + mingwen);

    }

    public static void local(String miwen,int[] miyao) {
        try (OutputStream  outputStream = new FileOutputStream("output.txt");){
/*            // 使用 ByteBuffer 将 int 数组转换为字节数组  乱码
            ByteBuffer byteBuffer = ByteBuffer.allocate(miyao.length * 4);
            for (int value : miyao) {
                byteBuffer.putInt(value);
            }
            byte[] byteArray = byteBuffer.array();*/
            StringBuilder sb = new StringBuilder();
            sb.append(miwen).append("\n");
            for(int i : miyao){
                sb.append(i);
                sb.append("-");
            }
            sb.deleteCharAt(sb.length()-1);

            outputStream.write(sb.toString().getBytes());



        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static int[] miyao(int length) {
        Random r = new Random();
        int[] ans = new int[length];
        for (int i = 0; i < length; i++) {
            ans[i] = r.nextInt(26);
        }
        return ans;
    }

    public static String jiami(String str, int[] miyao, ListNodes ln) {
        // 线程不安全，性能更高
        StringBuilder sb = new StringBuilder();
        Node02 temp = ln.head;
        for (int i = 0; i < str.length(); i++) {
            String s = String.valueOf(str.charAt(i));

            while (!s.equals(temp.data)) {
                temp = temp.next;
            }
            for (int j = 0; j < miyao[i]; j++) {
                temp = temp.next;
            }
            sb.append(temp.data);
        }
        return sb.toString();
    }

    // 可以修改为读取文件解密
    public static String jiemi(String str, int[] miyao, ListNodes ln) {
        // 线程不安全，性能更高
        StringBuilder sb = new StringBuilder();
        Node02 temp = ln.head;
        for (int i = 0; i < str.length(); i++) {
            String s = String.valueOf(str.charAt(i));

            while (!s.equals(temp.data)) {
                temp = temp.next;
            }
            for (int j = 0; j < miyao[i]; j++) {
                temp = temp.pre;
            }
            sb.append(temp.data);
        }
        return sb.toString();
    }

    public static ListNodes init() {
        ListNodes ln = new ListNodes();
        for (int i = 0; i < 26; i++) {
            Node02 n = new Node02();
            n.data = String.valueOf((char) ('a' + i));
            ln.addNode(n);
        }
        return ln;
    }

}

class ListNodes {
    Node02 head;

    public boolean addNode(Node02 n) {
        if (null == head) {
            head = n;
            n.pre = n;
            n.next = n;
        }
        Node02 temp = head;
        while (temp.next != head) {
            temp = temp.next;
        }
        temp.next = n;
        n.next = head;
        n.pre = temp;
        head.pre = n;
        return true;

    }

}

class Node02 {
    Node02 pre;
    Node02 next;
    String data; // 这里假定不能使用char （不然加密方式看着太傻）
}