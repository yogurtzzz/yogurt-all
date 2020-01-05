//package sort.impl.other;
//
//import utils.YogurtArrays;
//
///**
// * 时间复杂度 O(d*(n+r))
// * 空间复杂度 O(n+r)
// * d为
// * 基数排序： 是一种非比较的排序
// * 从最高位或者最低位开始
// * 每次按照某一位数字进行排序
// * 从最高位开始：MSD（Most Significant Digital）  适用于位数多的数列
// * 从最低位开始：LSD（Least Significant Digital） 适用于位数少的序列
// * 代码实现：进行N次  “分配”与“收集”（N为数列最大数的位数）
// * 需要开辟10个桶（编号0-9），每个桶里存放对应位数是该桶编号的数  （若要支持负数，则需要20个桶）
// *
// */
//public class RadixSort extends TimingSort {
//    @Override
//    public void doSort(int[] arr) {
//        radixSort(arr);
//    }
//
//    private void radixSort(int[] arr){
//        if (arr.length <= 1)
//            return ;
//        int max = arr[0];
//        for (int i = 1;i < arr.length; i++){
//            if (arr[i] > max)
//                max = arr[i];
//        }
//
//        int digitNum = 0;
//        while(max > 0){
//            max /= 10;
//            digitNum++;
//        }
//
//        int[][] buckets = new int[19][arr.length];
//
//
////桶编号      -9   -8   -7   -6   -5   -4   -3   -2   -1   0   1    2   3    4    5    6    7    8    9
////数组下标     0    1    2    3    4    5    6    7    8   9   10   11  12   13   14   15   16   17   18
//        int base = 1;
//        for (int i = 0; i < digitNum; i++){
//            //记录每个桶中的元素个数
//            int[] bucketsSize = new int[19];
//            //一趟分配，将数列中的数放到不同的桶中
//            for (int j = 0;j < arr.length; j++){
//                //从最低位开始
//                int whichBucket = (arr[j] / base) % 10;
//                //前9位用来存负数位
//                //后10位用来存正数位
//                //所以offset 为 9
//                whichBucket += 9;
//                buckets[whichBucket][bucketsSize[whichBucket]++] = arr[j];
//            }
//
//            //一趟收集,从所有桶中收集元素
//            int pos = 0;
//            for (int j = 0;j < 19; j++){
//                for (int k = 0; k < bucketsSize[j];k++){
//                    arr[pos++] = buckets[j][k];
//                }
//            }
//            base *= 10;
//        }
//    }
//
//    public static void main(String[] args) {
//        int[] arr = {-2,5,-11,7,4,23,-36,24};
//        RadixSort sort = new RadixSort();
//        sort.radixSort(arr);
//        YogurtArrays.showArrays(arr);
//    }
//}
