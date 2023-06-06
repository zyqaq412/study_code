/**
 *
 * @title: MergeSort 归并排序
 * @Author zxwyhzy
 * @Date: 2023/6/6 22:15
 * @Version 1.0
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {2,2,4,5,7,1,13,34,5};
        sort(arr,0,arr.length-1);
        for(int a : arr){
            System.out.print(a+" ");// 1 2 2 4 5 5 7 13 34
        }
    }

    // 分解
    public static void sort(int[] arr, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end) / 2;
        sort(arr, start, mid);
        sort(arr, mid + 1, end);
        marge(arr, start, mid, end);

    }

    // 合并
    public static void marge(int[] arr, int start, int mid, int end) {
        int[] temp = new int[end - start + 1];
        int leftIndex = start;
        //  在归并排序中，mid 是分割数组的索引，而不是包含在右侧子数组中的索引。
        //  因此，rightIndex 应该初始化为 mid + 1 而不是 mid。
        //  rightIndex = mid;
        int rightIndex = mid+1;
        int p = 0;
        while (leftIndex <= mid && rightIndex <= end) {
            if (arr[leftIndex] <= arr[rightIndex]) {
                temp[p++] = arr[leftIndex++];
            } else {
                temp[p++] = arr[rightIndex++];
            }
        }

        if (leftIndex > mid) {
            while (rightIndex <= end) {
                temp[p++] = arr[rightIndex++];
            }
        }

        if (rightIndex > end) {
            while (leftIndex <= mid) {
                temp[p++] = arr[leftIndex++];
            }
        }


        for (int i = 0; i < temp.length; i++) {
            arr[i+start] = temp[i];
        }
    }
}
