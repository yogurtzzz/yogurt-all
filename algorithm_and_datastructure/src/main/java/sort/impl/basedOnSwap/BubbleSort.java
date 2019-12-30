package sort.impl.basedOnSwap;

import sort.core.TimingSort;

public class BubbleSort extends TimingSort {

    @Override
    public void doSort(int[] arr) {
        bubbleSort(arr);
    }

    //核心实现
    private void bubbleSort(int[] arr){
        //每次冒泡一个最大的上去
        for (int i = arr.length - 1; i > 0; i--){
            for (int j = 0; j < i; j++){
                if (arr[j] > arr[j+1])
                    swap(arr,j,j+1);
            }
        }
    }
}
