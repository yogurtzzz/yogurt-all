package sort.test;

import org.junit.Test;
import sort.core.Sort;
import sort.core.TimingSort;
import sort.impl.basedOnInsertion.DirectInsertSort;
import sort.impl.basedOnInsertion.ShellSort;
import sort.impl.basedOnSelection.SimpleSelectSort;
import sort.impl.basedOnSwap.BubbleSort;
import sort.impl.other.HeapSort;

import java.util.ArrayList;

/**
 * 在数据量较大情况下，测试表明
 * 性能 shellSort ≈ heapSort > selectSort ≈ insertSort > bubbleSort
 * 希尔排序 ≈ 堆排序 > 简单选择排序 ≈ 直接插入排序 > 冒泡排序
 */
public class SortTest {

    private static ArrayList<int[]> dataToTest;
    private ArrayList<int[]> dataForEachTest;

    static {
        //只初始化一次
        //此次随机生成的数组，用于每个test用例
        dataToTest = new ArrayList<>();
        int[] a1 = randomArr(1,2,0);
        int[] a2 = randomArr(1,2,1);
        int[] a3 = randomArr(0,200000,20000);
        int[] a4 = randomArr(-500,2000,1500);
//        dataToTest.add(a1);
//        dataToTest.add(a2);
        dataToTest.add(a3);
//        dataToTest.add(a4);
//        System.out.println("初始状态 : ");
        showInitArray();
    }

    {
        //初始化块
        //每个test实例执行前，都会重新实例化本类
        //确保执行每个test用例时，使用的都是同样的数组
        //深度拷贝
        dataForEachTest = completelyDeepCopy(dataToTest);
    }
    private ArrayList<int[]> completelyDeepCopy(ArrayList<int[]> src){
        ArrayList<int[]> result = new ArrayList<>();
        for (int[] item : src){
            int size = item.length;
            int[] newItem = new int[size];
            for (int i = 0; i < size; i++){
                newItem[i] = item[i];
            }
            result.add(newItem);
        }
        return result;
    }

    private static int[] randomArr(int from,int to,int size){
        if (from >= to)
            throw new IllegalArgumentException("起始值必须小于结束值！");
        if (size < 0)
            throw new IllegalArgumentException("数组大小必须大于等于0");
        if (size == 0)
            return new int[]{};
        int gap = to - from;
        int[] arr = new int[size];
        for (int i = 0 ; i < size; i++){
            int item = (int)(Math.random() * gap) + from;
            arr[i] = item;
        }
        return arr;
    }

    private void batchSort(Sort sortUtil){
        for (int[] arr : dataForEachTest){
            sortUtil.sort(arr);
        }
    }
    //会计算耗时
    private void batchTimingSort(TimingSort timingSort){
        for (int[] arr : dataForEachTest){
            timingSort.sort(arr);
        }
    }
    private static void showInitArray(){
        System.out.println("初始化数组");
        int i = 1;
        for (int[] a : dataToTest){
            System.out.print("array" + (i++) + " : ");
            for (int ai : a)
                System.out.print(ai + " ");
            System.out.println();
        }
        System.out.println("===================================================");
    }
    private void showResult(){
        System.out.println("===================================================");
        String arr = "array";
        int i = 1;
        for (int[] a : dataForEachTest){
            System.out.print("array" + (i++) + " : ");
            for (int ai : a)
                System.out.print(ai + " ");
            System.out.println();
        }
        System.out.println("===================================================");
    }

    @Test
    public void testBubbleSort(){
        TimingSort bubbleSort = new BubbleSort();
        System.out.println("#Bubble Sort#");
        batchTimingSort(bubbleSort);
        showResult();
    }

    @Test
    public void testShellSort(){
        System.out.println("#Shell Sort#");
        TimingSort shellSort = new ShellSort();
        batchTimingSort(shellSort);
        showResult();
    }
    @Test
    public void testInsertSort(){
        TimingSort insertSort = new DirectInsertSort();
        System.out.println("#Direct Insert Sort#");
        batchTimingSort(insertSort);
        showResult();
    }

    @Test
    public void testSimpleSelectSort(){
        TimingSort simpleSelectSort = new SimpleSelectSort();
        System.out.println("#Simple Select Sort#");
        batchTimingSort(simpleSelectSort);
        showResult();
    }

    @Test
    public void testHeapSort(){
        TimingSort heapSort = new HeapSort();
        System.out.println("#Heap Sort#");
        batchTimingSort(heapSort);
        showResult();
    }

}
