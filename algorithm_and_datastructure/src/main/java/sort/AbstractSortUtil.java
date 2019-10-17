package sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class AbstractSortUtil implements SortUtil {

    //Template Method Pattern
    public abstract void doSort(int[] arr);

    public void inputAndTest(){
        Scanner scanner = new Scanner(System.in);
        int n ;
        List<Integer> list = new ArrayList<>();
        while ((n = scanner.nextInt()) != -1){
            list.add(n);
        }
        int[] arr = list.stream().mapToInt(Integer::valueOf).toArray();
        doSort(arr);
        for (int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }

    //swap two number
    protected void swap(int[] arr,int i ,int j){
        int size = arr.length;
        if (i >= size || j >= size)
            throw new IndexOutOfBoundsException("i = " + i + ",j = " + j + ",but size = " +size);
        //most efficient method for swap two numbers
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
