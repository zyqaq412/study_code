/**
 * @title: BubbleSort 冒泡排序
 * @Author zxwyhzy
 * @Date: 2023/6/6 22:39
 * @Version 1.0
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {2,2,4,5,7,1,13,34,5};
        sort(arr);
        for(int a : arr){
            System.out.print(a+" ");// 1 2 2 4 5 5 7 13 34
        }
    }
    public static void sort(int[] arr){
        int n = arr.length;
        // n 个数据只需要比较 n-1 次
        for (int i = 0; i < n - 1; i++) { // 进行 n-1 轮排序
            for (int j = 0; j < n - i - 1; j++) { // 在未排序的部分中进行比较和交换
                if (arr[j] > arr[j+1]){ // 如果前一个元素大于后一个元素
                    int temp = arr[j]; // 交换两个元素的位置
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}
