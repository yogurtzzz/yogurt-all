package magical;

import base.SwapCapable;

import java.util.HashMap;
import java.util.Map;

/**
 * KnuthShuffle 随机洗牌算法
 * 据说能做到绝对公平
 * 每个数字，在每个位置上出现的概率，是相等的
 */
public class KnuthShuffle extends SwapCapable {

    public void shuffle(int[] arr){
        for (int i = arr.length - 1; i >= 0; i--){
            /**
             * 一个长度为n的数组
             * 从最后一个位置开始，每次将该位置的数字，与它前面的随机一个位置（包含它本身的位置）的数字，做交换
             * 那么某一数字出现在最后一个位置的概率是  1/n
             * 随后，从剩余的n - 1个数字中，随机抽取一个数，放在倒数第二个位置
             * 那么某一数字出现在倒数第二个位置的概率是（第一次时没被抽中×这次被抽中）  (n-1)/n * 1/(n-1) = 1/n
             * .... 以此类推 ....   每个数字出现在每个位置上的概率，都是 1/n
             * 当然，这依赖于 【随机抽取一个数】的过程是公平的
             */
            int rand = (int)(Math.random() * (i + 1));
            swap(arr,i,rand);
        }
    }

    private static class Bucket{
        private int[] bucket = new int[6];
        public int[] getBucket(){
            return this.bucket;
        }
    }
    public static void main(String[] args) {
        Map<Integer,Bucket> countMap = new HashMap<>();
        KnuthShuffle knuthShuffle = new KnuthShuffle();
        int[] sum = new int[6];
        countMap.put(0,new Bucket());
        countMap.put(1,new Bucket());
        countMap.put(2,new Bucket());
        countMap.put(3,new Bucket());
        countMap.put(4,new Bucket());
        //在某一位置出现的，数字以及对应的次数
        int n = 100000;
        for (int i = 0;i < n; i++){
            //对 1  - 5 进行洗牌
            int[] arr = {1,2,3,4,5};
            knuthShuffle.shuffle(arr);
            countMap.get(0).getBucket()[arr[0]]++;
            countMap.get(1).getBucket()[arr[1]]++;
            countMap.get(2).getBucket()[arr[2]]++;
            countMap.get(3).getBucket()[arr[3]]++;
            countMap.get(4).getBucket()[arr[4]]++;
        }

        sum[0] = countMap.get(0).getBucket()[1] + countMap.get(0).getBucket()[2] + countMap.get(0).getBucket()[3] + countMap.get(0).getBucket()[4] + countMap.get(0).getBucket()[5];
        sum[1] = countMap.get(1).getBucket()[1] + countMap.get(1).getBucket()[2] + countMap.get(1).getBucket()[3] + countMap.get(1).getBucket()[4] + countMap.get(1).getBucket()[5];
        sum[2] = countMap.get(2).getBucket()[1] + countMap.get(2).getBucket()[2] + countMap.get(2).getBucket()[3] + countMap.get(2).getBucket()[4] + countMap.get(2).getBucket()[5];
        sum[3] = countMap.get(3).getBucket()[1] + countMap.get(3).getBucket()[2] + countMap.get(3).getBucket()[3] + countMap.get(3).getBucket()[4] + countMap.get(3).getBucket()[5];
        sum[4] = countMap.get(4).getBucket()[1] + countMap.get(4).getBucket()[2] + countMap.get(4).getBucket()[3] + countMap.get(4).getBucket()[4] + countMap.get(4).getBucket()[5];
        for (int i = 0; i < 5; i++){
            System.out.println("位置 " + i);
            System.out.printf("1出现的概率：%.3f\n", (double)countMap.get(i).getBucket()[1] / sum[i]);
            System.out.printf("2出现的概率：%.3f\n" ,(double)countMap.get(i).getBucket()[2] / sum[i]);
            System.out.printf("3出现的概率：%.3f\n" ,(double)countMap.get(i).getBucket()[3] / sum[i]);
            System.out.printf("4出现的概率：%.3f\n" ,(double)countMap.get(i).getBucket()[4] / sum[i]);
            System.out.printf("5出现的概率：%.3f\n" ,(double)countMap.get(i).getBucket()[5] / sum[i]);
        }
    }
}
