import java.util.Arrays;

public class KnapsackProblem2 {
    public static void main(String[] args) {
        //物品重量
        int[] weight = gennerateArray(40);
        //物品的价值
        int[] value = gennerateArray(40);
        //背包的容量
        int capacity= 300;
        //物品的个数
        int n = weight.length;

        long stime = System.nanoTime();
        int maxValue = knapsack(weight, value, capacity,n-1);
        long etime = System.nanoTime();

        System.out.println("背包内最大的物品价值总和为：" + maxValue);
        System.out.printf("执行时长：%d 纳秒.", (etime - stime));
    }

    //    生成随机数组
    public static int[] gennerateArray(int len){
        int[] arr = new int[len];
        for (int i=0;i<arr.length;i++){
            arr[i]=1+(int)(Math.random()*100);
        }
        return arr;
    }

    //分治法背包问题
    public static int knapsack(int[] weight, int[] value, int capacity, int index){
        //索引无效或标包容量不足
        if(index<0||capacity<=0){
            return 0;
        }
        //不放置第index个物品的价值
        int maxValue = knapsack(weight, value, capacity, index-1);
        //放置第index个物品的价值
        if(weight[index]<=capacity){
            maxValue = Math.max(maxValue,knapsack(weight, value, capacity-weight[index], index-1)+value[index]);
        }
        return maxValue;
    }
}
