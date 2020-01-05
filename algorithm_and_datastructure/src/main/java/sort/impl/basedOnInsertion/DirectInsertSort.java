//package sort.impl.basedOnInsertion;
//
///**
// * 直接插入排序
// * 直接插入排序在小规模数据，或者数组基本有序时，比较高效
// */
//public class DirectInsertSort extends TimingSort {
//    @Override
//    public void doSort(int[] arr) {
//        directInsertSort(arr);
//    }
//    private void directInsertSort(int[] arr){
//        if (arr.length <= 1)
//            return;
//        //已排好序的大小
//        int sortedSize = 1;
//        //当前待插入的元素位置
//        //第二个元素
//        int pos = 1;
//
//        while(pos < arr.length){
//            int currentPos = pos;
//            //从后往前，在已有序的列表里查找合适的插入位置
//            for (int i = sortedSize - 1; i >= 0; i--){
//                //找到合适的插入位置
//                //这里插入还是基于交换
//                if (arr[i] > arr[currentPos]){
//                    swap(arr,i,currentPos);
//                    currentPos = i;
//                }else {
//                    break;
//                }
//            }
//            sortedSize++;
//            pos++;
//        }
//    }
//}
