package sort.impl.other;

import sort.core.TimingSort;

/**
 * 构建大根堆
 * 每次将堆顶元素与末尾元素交换
 * 并将剩余元素继续构造大根堆，再交换
 */
public class HeapSort extends TimingSort {
    @Override
    public void doSort(int[] arr) {
        heapSort(arr);
    }
    private void heapSort(int[] arr){
        initHeap(arr);
        for (int i = arr.length - 1 ;i > 0 ; i--){
            //将最大的数与最末尾的数交换
            //最末尾的数位置固定
            //调整前 n-1 个数 为大根堆
            swap(arr,0,i);
            adjustDown(arr,i - 1);
        }
    }

    //建堆
    private void initHeap(int[] arr){
        for (int i = 0 ; i < arr.length; i++){
            //插入第一个节点
            if (i == 0)
                continue;
            if (arr[i] > arr[(i - 1)/2]){
                //若新插入的节点比其父节点大，交换
                //并向上调整
                adjustUp(arr,i);
            }
        }
    }

    //建堆时向上调整
    private void adjustUp(int[] arr,int son){
        if (son < 0 || son >= arr.length)
            throw new IllegalArgumentException("待调整节点不存在");
        //获取其父节点
        int parent = (son - 1)/2;
        if (parent < 0)
            throw new IllegalArgumentException("待调整节点无父节点");
        while (parent >= 0){
            if (arr[son] > arr[parent]){
                swap(arr,son,parent);
                son = parent;
                parent = (parent - 1)/2;
            }else{
                //调整完毕
                return;
            }
        }
    }
    private void adjustDown(int[] arr,int end){
        int i = 0;
        while (i <= end){
            int leftSon = 2 * i + 1;
            int rightSon = leftSon + 1;
            if (leftSon > end)
                break;
            int maxPos;
            if (rightSon > end) {
                //只存在左儿子
                maxPos = leftSon;
            }
            else {
                maxPos = arr[leftSon] > arr[rightSon] ? leftSon : rightSon;
            }
            if (arr[maxPos] > arr[i]){
                swap(arr,maxPos,i);
                //循环往下调整
                i = maxPos;
            }else{
                //调整完毕
                break;
            }
        }
    }

    public static void main(String[] args) {
        HeapSort sort = new HeapSort();
        int[] arr = {8,5,7,2,23,11,15,98,46,14,17,-25,-2,-1,34};
        sort.heapSort(arr);
        for (int item : arr){
            System.out.print(item + " ");
        }
    }
}
