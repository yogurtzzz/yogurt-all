package sort.test;

import org.junit.AfterClass;
import org.junit.Test;
import sort.core.Sort;
import sort.core.TimingSort;
import sort.exception.IllegalSortResultException;
import sort.impl.basedOnInsertion.DirectInsertSort;
import sort.impl.basedOnInsertion.ShellSort;
import sort.impl.basedOnSelection.SimpleSelectSort;
import sort.impl.basedOnSwap.BubbleSort;
import sort.impl.basedOnSwap.QuickSort;
import sort.impl.other.HeapSort;
import sort.impl.other.MergeSort;
import sort.impl.other.RadixSort;
import utils.YogurtArrays;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 在数据量较大情况下，测试表明
 * 性能如下
 * quickSort > mergeSort > heapSort > shellSort > insertSort > selectSort > bubbleSort
 * 快速排序 > 归并排序 > 堆排序 > 希尔排序  > 直接插入排序 > 简单选择排序 > 冒泡排序
 * 前4个总体最快，在输入数组不同的情况下表现有些微不同
 */

/**
 * Junit执行
 * @Before：初始化方法 对于每一个测试方法都要执行一次（注意与BeforeClass区别，后者是对于所有方法执行一次）
 * @After：释放资源 对于每一个测试方法都要执行一次（注意与AfterClass区别，后者是对于所有方法执行一次）
 * @Test：测试方法，在这里可以测试期望异常和超时时间
 * @Test(expected=ArithmeticException.class)检查被测方法是否抛出ArithmeticException异常
 * @Ignore：忽略的测试方法
 * @BeforeClass：针对所有测试，只执行一次，且必须为static void
 * @AfterClass：针对所有测试，只执行一次，且必须为static void
 * 一个JUnit4的单元测试用例执行顺序为：
 * @BeforeClass -> @Before -> @Test -> @After -> @AfterClass;
 * 每一个测试方法的调用顺序为：
 * @Before -> @Test -> @After;
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
        int[] a3 = randomArr(-10000,20000,10000);
        int[] a4 = randomArr(-500,2000,1500);
//        dataToTest.add(a1);
//        dataToTest.add(a2);
        dataToTest.add(a3);
//        dataToTest.add(a4);
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
            //这里数组拷贝可以用Arrays.copyOf替换
            //int[] newArray = Arrays.copyOf(item,item.length);
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

    //批量排序
    private void batchSort(Sort sortUtil){
        for (int[] arr : dataForEachTest){
            sortUtil.sort(arr);
        }
    }
    private static void showInitArray(){
        System.out.println("初始化数组");
        int i = 1;
        for (int[] a : dataToTest){
            System.out.print("array" + (i++) + " : ");
            YogurtArrays.showArrays(a);
        }
        System.out.println("===================================================");
    }
    private void showResult(){
        int i = 1;
        for (int[] a : dataForEachTest){
            System.out.print("array" + (i++) + " : ");
            YogurtArrays.showArrays(a);
        }
        System.out.println("===================================================");
    }

    private void checkSortResult(){
        for (int[] arr : dataForEachTest){
            for (int i = 0;i < arr.length; i++){
                if (i != arr.length - 1){
                    if (arr[i] > arr[i + 1])
                        throw new IllegalSortResultException("排序结果不正确");
                }
            }
        }
    }
    private void commonTest(TimingSort sort){
        //20个宽度，左对齐
        System.out.printf("%-20s","#" + sort.getClass().getSimpleName() +"# ");
        batchSort(sort);
        checkSortResult();
        //showResult();
    }
    @Test
    public void testBubbleSort(){
        commonTest(new BubbleSort());
    }

    @Test
    public void testShellSort(){
        commonTest(new ShellSort());
    }

    @Test
    public void testInsertSort(){
        commonTest(new DirectInsertSort());
    }

    @Test
    public void testSimpleSelectSort(){
        commonTest(new SimpleSelectSort());
    }

    @Test
    public void testHeapSort(){
        commonTest(new HeapSort());
    }

    @Test
    public void testQuickSort(){
        commonTest(new QuickSort());
    }

    @Test
    public void testMergeSort(){
        commonTest(new MergeSort());
    }

    @Test
    public void testRadixSort(){
        commonTest(new RadixSort());
    }
}
