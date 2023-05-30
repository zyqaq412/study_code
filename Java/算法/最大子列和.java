
/**
 * @title: 最大子列和
 * @Author zxwyhzy
 * @Date: 2023/3/3 22:40
 * @Version 1.0
 */
public class 最大子列和 {

    // 慕课数据结构最大子列和
    public static void main(String[] args) {
        System.out.println(maxSubseqSum1(new int[]{1, 2, -6}, 3)); //3
        System.out.println(maxSubseqSum2(new int[]{1, 2, -6}, 3)); //3
        System.out.println(maxSubseqSum3(new int[]{1, 2, -6}, 3)); //3
      //  System.out.println(maxSubseqSum(new int[]{1, 2, -6}, 0,2)); //3
    }

    /**
     * 第一种算法
     * 时间复杂度：3层for循环嵌套 T(n)=O(n3)
     * @param a 数组
     * @param n 数组长度
     * @return
     */
    public static int maxSubseqSum1(int[] a, int n) {
        int max =0;

        for (int i = 0; i < n; i++) { // i是子列左端
            for (int j = i; j < n; j++) { // j是子类右端
                int sum = 0;
                for (int k = i; k <= j; k++) { // k遍历子列
                    sum += a[k];
                }
                max = max>sum ? max: sum;
            }
        }
        return max;
    }
    /**
     * 第二种算法
     * 时间复杂度：2层for循环嵌套 T(n)=O(n2)
     * @param a 数组
     * @param n 数组长度
     * @return
     */
    public static int maxSubseqSum2(int[] a, int n) {
        int max =0;

        for (int i = 0; i < n; i++) { // i是子列左端
            int sum = 0;
            for (int j = i; j < n; j++) { // j是子类右端
                sum += a[j];
                // int sum = 0;
                // j每次加1时不需要k从头加，只需要再加一个a[j]
                /*for (int k = i; k <= j; k++) { // k遍历子列
                    sum += a[k];
                }*/
                max = max>sum ? max: sum;
            }
        }
        return max;
    }

    /**
     *  在线算法
     *  时间复杂度：1层for循环嵌套 T(n)=O(n)
     *
     * @param a
     * @param n
     * @return
     */
    public static int maxSubseqSum3(int[] a, int n){
        int max = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum +=a[i];
            if (sum > max) max = sum;
            if (sum < 0) sum = 0;
        }
        return max;
    }
    /**
     *  分治算法
     */


}
class Solution {
    public static void main(String[] args) {
         int[] arr = {-3,1,0,-1,-2,3,2,-1};
        // int[] arr = {-2,-1};
        int i = new Solution().maxSubArray(arr);
        System.out.println(i);
    }
    public int maxSubArray(int[] nums) {
        if(nums.length==1) return nums[0];
        int a = maxSubseqSum(nums,0,nums.length-1);
        return a;
    }
    public static int maxSubseqSum(int[] a, int l,int r){
        if (l==r) return a[l];
        int mid = (l+r)/2;

        int ll =maxSubseqSum(a,l,mid);
        int rr =maxSubseqSum(a,mid+1,r);

        int leftMax= -100 ,leftSum = 0;
        // 向左扫描
        for (int i = mid; i >= l; i--) {
            leftSum += a[i];
            if (leftMax < leftSum) leftMax = leftSum;
        }

        int rightMax= -100 ,rightSum = 0;
        // 向右扫描
        for (int i = mid+1; i <= r; i++) {
            rightSum += a[i];
            if (rightMax < rightSum) rightMax = rightSum;
        }
        int midMax = Math.max(rightMax+leftMax,Math.max(rightMax,leftMax));

        return Math.max(Math.max(ll,rr),midMax);
    }
    // 这里不应该把求跨边境的最大值提出来 或者应该将 左右边界传入函数
    // 这样写会导致每次求跨边境最大值都将整个数组遍历
/*    public static int midMax(int[] a,int mid){
        int leftMax= Integer.MIN_VALUE ,leftSum = 0;
        // 向左扫描
        for (int i = mid; i >= 0; i--) {
            leftSum += a[i];
            if (leftMax < leftSum) leftMax = leftSum;
        }

        int rightMax= Integer.MIN_VALUE ,rightSum = 0;
        // 向右扫描
        for (int i = mid+1; i < a.length; i++) {
            rightSum += a[i];
            if (rightMax < rightSum) rightMax = rightSum;
        }

        return Math.max(rightMax+leftMax,Math.max(rightMax,leftMax));
    }*/

}
