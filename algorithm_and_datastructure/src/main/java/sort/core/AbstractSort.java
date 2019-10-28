package sort.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class AbstractSort implements Sort {

    //Template Method Pattern
    public abstract void doSort(int[] arr);

    public void sort(int[] arr){
        this.doSort(arr);
    }

    public void inputAndTest(){
        Scanner scanner = new Scanner(System.in);
        int n ;
        List<Integer> list = new ArrayList<>();
        System.out.println("输入数组，输入-1表示结束");
        while ((n = scanner.nextInt()) != -1){
            list.add(n);
        }
        int[] arr = list.stream().mapToInt(Integer::valueOf).toArray();
        sort(arr);
        for (int i : arr){
            System.out.print(i + " ");
        }
        System.out.println();
    }

    //swap two number
    protected void swap(int[] arr,int i ,int j){
        if (i == j)
            return;
        //该方法有个前提
        //i和j不能相等，否则将出错
        int size = arr.length;
        if (i >= size || j >= size || i < 0 || j < 0)
            throw new IndexOutOfBoundsException("i = " + i + ",j = " + j + ",but size = " +size);
        //most efficient method for swap two numbers
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }
}
