import java.util.Random;

/**
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线玩家A和玩家B依次拿走每张纸牌
 * 规定玩家A先拿，玩家B后拿
 * 但是每个玩家每次只能拿走最左或最右的纸牌玩家A和玩家B都绝顶聪明
 * 请返回最后获胜者的分数。
 */
public class no_2 {
    public static void main(String[] args) {
        //int[] arr = {50,100,20,10};
        int N = 2000;
        int[] arr = new int[N];
        Random r = new Random();
        for (int i = 0; i < N; i++) {
            arr[i] = r.nextInt(N+100);
        }


     //   timeCount1(arr);
        timeCount2(arr);
        timeCount22(arr);
        timeCount3(arr);
    }
    public static void timeCount1(int[] arr){
        long start = System.currentTimeMillis();
        test(arr, 0, arr.length-1);
        long end = System.currentTimeMillis();
        System.out.println("方法1耗时："+(end-start));
    }
    public static void timeCount2(int[] arr){
        long start = System.currentTimeMillis();
        test2(arr, 0, arr.length-1);
        long end = System.currentTimeMillis();
        System.out.println("方法2耗时："+(end-start));
    }
    public static void timeCount22(int[] arr){
        long start = System.currentTimeMillis();
        test22(arr, 0, arr.length-1);
        long end = System.currentTimeMillis();
        System.out.println("方法22耗时："+(end-start));
    }
    public static void timeCount3(int[] arr){
        long start = System.currentTimeMillis();
        test3(arr);
        long end = System.currentTimeMillis();
        System.out.println("方法3耗时："+(end-start));
    }



    public static void test3(int[] arr){
        int N = arr.length;
        int[][] fmap=new int[N][N];
        int[][] gmap=new int[N][N];
        for (int i = 0; i < gmap.length; i++) {
            fmap[i][i] = arr[i];
        }
        for(int i = 1;i<N;i++){
            int l =0;
            int r = i;
            while(r<N){
                fmap[l][r]=Math.max(arr[l]+gmap[l+1][r], arr[r]+gmap[l][r-1]);
                gmap[l][r]=Math.min(fmap[l+1][r], fmap[l][r-1]);
                l++;
                r++;
            }
        }
        

        if(fmap[0][N-1]==gmap[0][N-1]){
            System.out.println("平局："+fmap[0][N-1]);
        }else if(fmap[0][N-1]>gmap[0][N-1]){
            System.out.println("先手胜利："+fmap[0][N-1]);
        }else{
            System.out.println("后手胜利："+gmap[0][N-1]);
        }
    }











    public static void test2(int[] arr,int l,int r){
        int N = arr.length;
        int[][] fmap=new int[N][N];
        int[][] gmap=new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fmap[i][j] = -1;
                gmap[i][j] = -1;
            }
        }



        int a =f2(arr, l, r,fmap);

        int b=g2(arr, l, r,gmap);

        if(a==b){
            System.out.println("平局："+a);
        }else if(a>b){
            System.out.println("先手胜利："+a);
        }else{
            System.out.println("后手胜利："+b);
        }
    }


    public static int f2(int[] arr,int l,int r,int[][] fmap){
        if(l==r) return arr[l];
        if(fmap[l][r]!=-1) return fmap[l][r];
        

        // 先手拿左边牌后 下一次就是后手在l+1到r中拿牌
        int left = arr[l]+g2(arr, l+1, r,fmap);
        int right = arr[r]+g2(arr, l, r-1,fmap);
        fmap[l][r] = left>right?left:right;
        return fmap[l][r];

    }

    public static int g2(int[] arr,int l,int r,int[][] gmap){
        if(l==r) return 0;
        if(gmap[l][r]!=-1) return gmap[l][r];
        
        int left = f2(arr, l+1, r,gmap);
        int right = f2(arr, l, r-1,gmap);
        // 后手选两数最小的因为大的被先手拿了
        gmap[l][r]=left<right?left:right;
        return gmap[l][r];
    }

    public static void test22(int[] arr,int l,int r){
        int N = arr.length;
        int[][] fmap=new int[N][N];
        int[][] gmap=new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                fmap[i][j] = -1;
                gmap[i][j] = -1;
            }
        }



        int a =f22(arr, l, r,fmap,gmap);

        int b=g22(arr, l, r,fmap,gmap);

        if(a==b){
            System.out.println("平局："+a);
        }else if(a>b){
            System.out.println("先手胜利："+a);
        }else{
            System.out.println("后手胜利："+b);
        }
    }


    public static int f22(int[] arr,int l,int r,int[][] fmap,int[][] gmap){
        if(l==r) return arr[l];
        if(fmap[l][r]!=-1) return fmap[l][r];
        

        // 先手拿左边牌后 下一次就是后手在l+1到r中拿牌
        int left = arr[l]+g22(arr, l+1, r,fmap,gmap);
        int right = arr[r]+g22(arr, l, r-1,fmap,gmap);
        fmap[l][r] = left>right?left:right;
        return fmap[l][r];

    }

    public static int g22(int[] arr,int l,int r,int[][] fmap,int[][] gmap){
        if(l==r) return 0;
        if(gmap[l][r]!=-1) return gmap[l][r];
        
        int left = f22(arr, l+1, r,fmap,gmap);
        int right = f22(arr, l, r-1,fmap,gmap);
        // 后手选两数最小的因为大的被先手拿了
        gmap[l][r]=left<right?left:right;
        return gmap[l][r];
    }



















    public static void test(int[] arr,int l,int r){
        
        int a =f(arr, l, r);

        int b=g(arr, l, r);

        if(a==b){
            System.out.println("平局："+a);
        }else if(a>b){
            System.out.println("先手胜利："+a);
        }else{
            System.out.println("后手胜利："+b);
        }
    }


    public static int f(int[] arr,int l,int r){
        if(l==r) return arr[l];

        // 先手拿左边牌后 下一次就是后手在l+1到r中拿牌
        int left = arr[l]+g(arr, l+1, r);
        int right = arr[r]+g(arr, l, r-1);

        return left>right?left:right;

    }

    public static int g(int[] arr,int l,int r){
        if(l==r) return 0;
        int left = f(arr, l+1, r);
        int right = f(arr, l, r-1);
        // 后手选两数最小的因为大的被先手拿了
        return left<right?left:right;
    }

}
