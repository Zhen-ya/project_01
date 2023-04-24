import java.util.Arrays;

public class KnapsackProblem1 {
    public static void main(String[] args) {
        //物品重量
        int[] weight = gennerateArray(5);
        //System.out.println("weight:" + Arrays.toString(weight));
        //物品的价值
        int[] value = gennerateArray(5);
        //System.out.println("value:" + Arrays.toString(value));
        //背包的容量
        int capacity= 100;
        //物品的个数
        int n = weight.length;

        knapsack(weight, value, capacity,n);
    }

    //    生成随机数组
    public static int[] gennerateArray(int len){
        int[] arr = new int[len];
        for (int i=0;i<arr.length;i++){
            arr[i]=1+(int)(Math.random()*100);
        }
        return arr;
    }

    //动态规划背包问题
    public static void knapsack(int[] weight, int[] value, int capacity, int n){
        //从0开始保存
        int[][] maxValue = new int[n+1][capacity+1];

        long stime = System.nanoTime();
        //物品和背包容量为0时，价值为0
        for(int i = 0; i < capacity+1; i++){
            maxValue[0][i] = 0;
        }
        for(int i = 0; i < n+1; i++){
            maxValue[i][0] = 0;
        }

        //不处理第一行
        for(int i = 1; i <= n; i++){
            for (int j = 1; j <= capacity; j++){
                //当前物品重量大于包的容量，当前最大价值等于上一件的最大价值
                if(weight[i-1] > j){
                    maxValue[i][j] = maxValue[i-1][j];
                }
                else{
                    //如果当前物体重量小于包的容量
                    maxValue[i][j] = Math.max(maxValue[i-1][j],maxValue[i-1][j-weight[i-1]]+value[i-1]);
                }
            }
        }
        long etime = System.nanoTime();
        System.out.printf("执行时长：%d 纳秒.", (etime - stime));
        System.out.println();

        //打印动态规划表
        /*for(int i = 0; i < n + 1; i++){
            for(int j = 0; j < capacity+1; j++){
                System.out.print(maxValue[i][j] + "\t");
            }
            System.out.println();
        }*/

        //打印最优解
        int[] result = new int[n];
        //将数组所有元素初始化为0
        Arrays.fill(result,0);
        int j = capacity;
        for(int i = n; i > 0; i--){
            if(maxValue[i][j] > maxValue[i-1][j]){
                result[i-1] = 1;
                j = j - weight[i-1];
            }
        }
        System.out.println("result:" + Arrays.toString(result));

        //打印最大的物品价值总和
        System.out.println("背包内最大的物品价值总和为：" + maxValue[n][capacity]);
    }
}
