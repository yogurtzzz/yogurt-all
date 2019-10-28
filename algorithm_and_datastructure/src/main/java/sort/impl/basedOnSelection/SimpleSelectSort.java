package sort.impl.basedOnSelection;

import sort.core.TimingSort;

public class SimpleSelectSort extends TimingSort {
    @Override
    public void doSort(int[] arr) {
        simpleSelectSort(arr);
    }
    private void simpleSelectSort(int[] arr){
        if (arr.length <= 1)
            return;
        for (int i = 0;i < arr.length; i++){
            int min = arr[i];
            int minPos = i;
            for (int j = i + 1;j < arr.length; j++){
                if (arr[j] < min){
                    min = arr[j];
                    minPos = j;
                }
            }
            if (i != minPos) {
                swap(arr, i, minPos);
            }
        }
    }
}
