package com.hzy.sort;

/**
 * @title: quickSort
 * @Author zxwyhzy
 * @Date: 2023/4/7 16:21
 * @Version 1.0
 */
public class quickSort {

    public static void main(String[] args) {
        int[] arr= {9,4,6,8,5,1,0,7,3,2,2};
        test(arr,0,arr.length-1);
        for(int i:arr){
            System.out.print(i+"\t");
        }
    }
    public static void test(int[] arr,int left,int right){

        if(left<=right){
            // 基数 选左边为基数
            int pivot = arr[left];
            int i = left;
            int j = right;
            while (i<j){
                // 如果基数在左，就先移动右指针，找到一个小于基数的值，将右指针的所指的值放在左指针所在位置
                while (i<j&&arr[j]>pivot) j--;
                if (i<j){
                    arr[i++] = arr[j];
                }
                // 右指针被操作后移动左指针，找到一个大于基数的值，交换到右指针的位置，右指针自减
                while (i<j&& arr[i] <pivot) i++;
                if (i<j){
                    arr[j--] = arr[i];
                }
            }
            // 左右指针重合时，将基数插入到重合位置，比基数小的数都在基数左边，大的数都在右边
            arr[i] = pivot;
            // 对左边数组排序
            test(arr,left,i-1);
            // 对右边数组排序
            test(arr,i+1,right);

        }

    }
}
