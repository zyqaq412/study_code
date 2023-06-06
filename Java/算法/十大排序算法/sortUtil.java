import java.util.Random;

/**
 * @title: sortUtil
 * @Author zxwyhzy
 * @Date: 2023/6/6 22:16
 * @Version 1.0
 */
public class sortUtil {
    public static void main(String[] args) {
        int[] arr = getArray();
        for(int a : arr){
            System.out.print(a + " ");
        }

    }

    public static int[] getArray(){
        Random random = new Random(1);
        int[] arr = new int[100000];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(1000000);
        }
        return arr;
    }
}
