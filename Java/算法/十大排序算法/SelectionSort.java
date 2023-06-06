/**
 * @title: SelectionSort 选择排序
 * @Author zxwyhzy
 * @Date: 2023/6/6 22:42
 * @Version 1.0
 */
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = {2,2,4,5,7,1,13,34,5};
        sort(arr);
        for(int a : arr){
            System.out.print(a+" ");// 1 2 2 4 5 5 7 13 34
        }
    }

    public static void sort(int[] arr) {
        int length = arr.length;
        for (int i = 0; i < length - 1; i++) { // 遍历数组
            int min = i; // 假设当前元素是最小值
            for (int j = i+1; j < length; j++) { // 在未排序的部分中找到最小的元素
                if (arr[j] < arr[min]){ // 如果找到了一个更小的元素
                    min = j; // 更新最小元素的索引
                }
            }
            if (min != i){ // 如果当前元素不是最小元素
                int temp = arr[i]; // 交换当前元素和最小元素的位置
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
    }

}
