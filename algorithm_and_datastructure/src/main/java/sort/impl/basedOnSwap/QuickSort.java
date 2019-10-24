package sort.impl.basedOnSwap;

import sort.core.TimingSort;

/**
 * Incomplete
 * TODO
 */
public class QuickSort extends TimingSort {
    @Override
    public void doSort(int[] arr) {
        quickSort(arr,0,arr.length - 1);
    }

    private void quickSort(int[] arr,int left,int right){
        if (left + 1 <= right)
            return ;
        int pivot = arr[left];
        int pivotPos = left;
        int posL = left + 1;
        int posR = right;
        boolean moveRight = true;
        while (posL != posR){
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
    }
}
