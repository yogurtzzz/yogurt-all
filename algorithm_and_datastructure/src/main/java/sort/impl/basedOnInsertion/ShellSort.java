package sort.impl.basedOnInsertion;

import sort.core.TimingSort;

/**
 * 希尔排序，又称缩小增量排序
 * 用一个增量，将数组分为若干组
 * 对各组元素进行直接插入排序
 * 缩小增量，重复上述步骤，直至增量为1，完成排序
 * 是对插入排序的一种改进
 * 通过跳跃式插入，使得每次插入排序时各个分组都是基本有序
 * 而插入排序在数据基本有序时效率很高
 *
 * 增量序列的选择非常重要
 * 较为常见的为 不断的平分数组长度，直至为1
 */
public class ShellSort extends TimingSort {
    @Override
    public void doSort(int[] arr) {
        shellSort(arr);
    }

    private void shellSort(int[] arr){
        int gap = arr.length / 2;
        while (gap >= 1){
            insertSortWithGap(arr,gap);
            gap /= 2;
        }
    }

    private void insertSortWithGap(int[] arr,int gap){
        for (int i = 0; i < gap; i++){
            //共有gap个组
            //对每组分别进行插入排序
            int j = i;
            int sortedSize = 1;
            while(j + gap < arr.length){
                int curPos = j + gap;
                for (int k = (sortedSize - 1) * gap + i; k >= 0; k -= gap){
                    if (arr[k] > arr[curPos]){
                        swap(arr,k,curPos);
                        curPos = k;
                    }else
                        break;
                }
                j += gap;
                sortedSize++;
            }
        }
    }
}
