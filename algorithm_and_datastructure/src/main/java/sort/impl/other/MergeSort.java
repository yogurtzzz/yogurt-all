//package sort.impl.other;
//
//import utils.YogurtArrays;
//
///**
// * 归并排序
// * Divide and conquer
// * 分而治之
// * 基本思想是：将2个有序表，合成一个有序表
// * 实际操作：拆分 + 合并
// * 注意需要一个辅助数组
// * 空间复杂度为 O(n)
// */
//public class MergeSort extends TimingSort {
//    private int[] arrCopy;
//    @Override
//    public void doSort(int[] arr) {
//        arrCopy = new int[arr.length];
//        mergeSort(arr,0,arr.length - 1);
//    }
//
//    private void mergeSort(int[] arr,int left,int right){
//        //注意写成left >= right，这样在数组为空时，也能正常运行
//        //先前写成 left == right ，导致在数组为空时，发生StackOverflow
//        if (left >= right)
//            return;
//        int mid = (left + right) / 2;
//        mergeSort(arr,left,mid);
//        mergeSort(arr,mid + 1,right);
//        merge(arr,left,mid,right);
//    }
//
//    private void merge(int[] arr,int left,int mid,int right){
//        System.arraycopy(arr,left,arrCopy,left,right - left +1);
//        int posL = left;
//        int posR = mid + 1;
//        int i = left;
//        while (posL <= mid && posR <= right){
//            if (arrCopy[posL] <= arrCopy[posR]){
//                arr[i++] = arrCopy[posL];
//                posL++;
//            }else{
//                arr[i++] = arrCopy[posR];
//                posR++;
//            }
//        }
//        while (posL <= mid){
//            //左子序列还未添加完，直接追加
//            arr[i++] = arrCopy[posL++];
//        }
//        while (posR <= right){
//            //右子序列还未添加完，直接追加
//            arr[i++] = arrCopy[posR++];
//        }
//    }
//
//    public static void main(String[] args) {
//        MergeSort mergeSort = new MergeSort();
//        int[] arr = {-8,5,2,14,23,25,-34,1};
//        mergeSort.doSort(arr);
//        YogurtArrays.showArrays(arr);
//    }
//}
