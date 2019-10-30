package sort.core;

import base.SwapCapable;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class AbstractSort extends SwapCapable implements Sort {

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
}
