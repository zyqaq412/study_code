package com.hzy;

/**
 * @title: Client
 * @Author zxwyhzy
 * @Date: 2023/5/24 22:32
 * @Version 1.0
 */
// Client.java

public class Client {
    public static void main(String[] args) {
        Activity activity = new Activity(1);

        int i = 10;

        while (i-- > 0){
            System.out.println("------------");
            activity.deductMoney();
            activity.raffle();
           // activity.dispensePrize();
            System.out.println("------------");

        }
    }
}

