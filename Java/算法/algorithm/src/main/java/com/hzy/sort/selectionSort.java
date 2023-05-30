package com.hzy.sort;

/**
 * @title: selectionSort 选择排序
 * @Author zxwyhzy
 * @Date: 2023/4/6 18:44
 * @Version 1.0
 */
public class selectionSort {
    public static void main(String[] args) {
        int[] arr = {1,33,54,78,3,6,6,83,2,6};
        test(arr);
        for (int n:arr) {
            System.out.print(n+"\t");
        }
    }

    public static void test(int[] arr){
        int minIndex;
        // n个数比较n-1次
        for (int i = 0; i < arr.length-1; i++) {
            // 每次拿未排序的第一个数与未排序的其他数比较
            minIndex = i;
            // 前面的数已经排序了 所以从 i+1 开始
            for (int j = i+1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) minIndex = j;
            }
            if (i!=minIndex){
                arr[i] += arr[minIndex];
                arr[minIndex] = arr[i] - arr[minIndex];
                arr[i] -= arr[minIndex];
            }

        }
    }
}
