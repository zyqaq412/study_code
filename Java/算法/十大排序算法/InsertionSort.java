/**
 * @title: InsertionSort 插入排序
 * @Author zxwyhzy
 * @Date: 2023/6/6 23:34
 * @Version 1.0
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] arr = {2,2,4,5,7,1,13,34,5};
        sort(arr);
        for(int a : arr){
            System.out.print(a+" ");// 1 2 2 4 5 5 7 13 34
        }
    }

    public static void sort(int[] arr) {
        int n = arr.length;
        // 在每次迭代中，将当前元素（key）与已排序的子数组进行比较，
        // 将大于key的元素向后移动，直到找到key的正确位置并插入。
        for (int i = 1; i < n; i++) {
            int key = arr[i]; // 当前要插入的元素
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j]; // 将大于key的元素向后移动
                j--;
            }

            arr[j + 1] = key; // 将key插入到合适的位置
        }
    }

}
