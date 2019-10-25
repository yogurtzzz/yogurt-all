package sort.core;

public abstract class TimingSort extends AbstractSort {
    @Override
    public void sort(int arr[]){
        long start = System.nanoTime();
        this.doSort(arr);
        long end = System.nanoTime();
        System.out.println((end - start)/1000 + " us");
    }
}
