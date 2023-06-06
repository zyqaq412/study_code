/**
 * @title: QuickSort 快速排序
 * @Author zxwyhzy
 * @Date: 2023/6/6 22:59
 * @Version 1.0
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {2, 2, 4, 5, 7, 1, 13, 34, 5};
        sort(arr, 0, arr.length - 1);
        for (int a : arr) {
            System.out.print(a + " ");// 1 2 2 4 5 5 7 13 34
        }
    }

    public static void sort(int[] arr, int left, int right) {
        if (left <= right) {
            int pivot = arr[left]; // 选择第一个元素作为基准元素（pivot）
            int l = left; // 左指针
            int r = right; // 右指针

            while (l < r) {
                while (l < r && arr[r] > pivot) { // 从右侧找到第一个小于等于pivot的元素
                    r--;
                }
                if (l < r) {
                    arr[l++] = arr[r]; // 将该元素放到左侧位置
                }

                while (l < r && arr[l] < pivot) { // 从左侧找到第一个大于等于pivot的元素
                    l++;
                }
                if (l < r) {
                    arr[r--] = arr[l]; // 将该元素放到右侧位置
                }
            }

            arr[l] = pivot; // 将主元放到正确的位置

            sort(arr, left, l - 1); // 对主元左侧的子数组进行排序
            sort(arr, l + 1, right); // 对主元右侧的子数组进行排序
        }
    }

}
