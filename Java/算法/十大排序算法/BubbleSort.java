/**
 * @title: BubbleSort
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
        int length = arr.length - 1;
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i; j++) {
                if (arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}
