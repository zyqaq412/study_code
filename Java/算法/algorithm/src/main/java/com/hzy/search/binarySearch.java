package com.hzy.search;

/**
 * @title: binarySearch 二分查找
 * @Author zxwyhzy
 * @Date: 2023/4/6 18:27
 * @Version 1.0
 */
public class binarySearch {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9};
        System.out.println(test(arr,4));// 3
        System.out.println(test(arr,11));// -1
    }

    /**
     *
     * @param arr 数据源
     * @param temp 查找数字
     * @return 查找数字下标  没有返回-1
     */
    public static int test(int[] arr,int temp){
        int low = 0, high = arr.length-1;
        int mid;
        while (low <= high){
            mid = (low+high)/2;
            if (arr[mid] == temp) return mid;
            else if (arr[mid] > temp) high = mid - 1;
            else low = mid + 1;
        }
        return -1;
    }
}
