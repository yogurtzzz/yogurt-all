package sort;


public class BubbleSort extends AbstractSortUtil {

    @Override
    public void doSort(int[] arr) {
        bubbleSort(arr);
    }

    public void bubbleSort(int[] arr){
        int size = arr.length;
        while(size-- > 0){
            for (int i = 0; i <= size; i++){
                if (i + 1 > size)
                    break;
                if (arr[i] > arr[i + 1]){
                    //swap
                    swap(arr,i,i + 1);
                }
            }
        }
    }
}
