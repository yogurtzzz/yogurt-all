package sort.impl.basedOnSwap;

import sort.core.TimingSort;

/**
 * Divide and conquer
 * 分而治之的思想
 * 1.从数列中挑选一个数，作为基准（pivot）
 * 2.将数列一分为二，小于pivot的，放在左边，大于pivot的放在右边
 * 3.对于pivot左右两边的子序列，递归地执行相同操作
 */
public class QuickSort extends TimingSort {
    @Override
    public void doSort(int[] arr) {
        quickSort(arr,0,arr.length - 1);
    }

    //核心实现
    private void quickSort(int[] arr,int left,int right){
        if (left >= right){
            //递归退出条件
            return ;
        }
        int pivot = arr[left];
        int pivotPos = left;
        int posL = left + 1;
        int posR = right;
        boolean moveRight = true;
        while (posL < posR + 1){
            //posL = posR + 1 时退出，划分完毕，左边全是小于pivot,右边全是大于pivot
            if (moveRight){
                if (arr[posR] < pivot){
                    swap(arr,posR,pivotPos);
                    pivotPos = posR;
                    moveRight = false;
                }
                posR--;
            }else{
                if (arr[posL] > pivot){
                    swap(arr,posL,pivotPos);
                    pivotPos = posL;
                    moveRight = true;
                }
                posL++;
            }
        }
        //划分完毕，左右两边子序列递归调用
        quickSort(arr,left,pivotPos - 1);
        quickSort(arr,pivotPos + 1,right);
    }
}
