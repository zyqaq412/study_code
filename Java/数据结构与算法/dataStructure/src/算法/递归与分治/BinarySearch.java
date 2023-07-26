package 算法.递归与分治;

/**
 * @title: BinarySearch 二分查找递归实现
 * @Author zxwyhzy
 * @Date: 2023/7/26 14:11
 * @Version 1.0
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,11,23,56,76};
        int target =  8;
        System.out.println(search(0,arr.length-1,target,arr));

    }

    public static int search(int begin,int end,int target,int[] arr){
        int mid = (begin+end)/2;
        if (arr[mid] == target){
            return mid;
        }else if (arr[mid] < target){
          return search(mid+1,end,target,arr);
        }else {
            return search(begin,mid-1,target,arr);
        }
    }
}
