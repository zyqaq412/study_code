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
        int mid = (start + end) / 2; // 计算数组的中间索引
        sort(arr, start, mid); // 对左侧子数组进行排序
        sort(arr, mid + 1, end); // 对右侧子数组进行排序
        merge(arr, start, mid, end); // 合并两个有序子数组
    }

    // 合并两个有序子数组
    public static void merge(int[] arr, int start, int mid, int end) {
        int[] temp = new int[end - start + 1]; // 创建临时数组来存储合并结果
        int leftIndex = start; // 左侧子数组的起始索引
        int rightIndex = mid + 1; // 右侧子数组的起始索引
        int p = 0; // 临时数组的索引

        while (leftIndex <= mid && rightIndex <= end) {
            if (arr[leftIndex] <= arr[rightIndex]) {
                temp[p++] = arr[leftIndex++]; // 将左侧子数组的元素放入临时数组
            } else {
                temp[p++] = arr[rightIndex++]; // 将右侧子数组的元素放入临时数组
            }
        }

        // 处理剩余的元素
        if (leftIndex > mid) {
            while (rightIndex <= end) {
                temp[p++] = arr[rightIndex++]; // 将剩余的右侧子数组元素放入临时数组
            }
        }

        if (rightIndex > end) {
            while (leftIndex <= mid) {
                temp[p++] = arr[leftIndex++]; // 将剩余的左侧子数组元素放入临时数组
            }
        }

        // 将临时数组中的元素复制回原数组的对应位置
        for (int i = 0; i < temp.length; i++) {
            arr[i + start] = temp[i];
        }
    }
}
