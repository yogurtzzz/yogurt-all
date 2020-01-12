package sort.core;

import static utils.ArrayUtil.assertSorted;
import static utils.ArrayUtil.printArray;

/**
 *  若要升序排序，需要构建大根堆，
 *  若要降序排序，需要构建大根堆
 * **/
public class HeapSort {
	/** 将堆存放在一个数组中 **/

	public static void main(String[] args) {
		HeapSort heapSort = new HeapSort();
		int[] arr = {7,8,9,1,3,5,2,-4,-5,-12,145,235,45,78,46,42};
		heapSort.heapSort(arr);
		printArray(arr);
		assertSorted(arr);
	}

	public void heapSort(int[] arr){
		buildHeap(arr);
		for (int i = arr.length - 1; i >= 0; i--){
			int temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;
			adjustDown(arr,0,i - 1);
		}
	}

	public void buildHeap(int[] arr){
		/** 一开始我是从0号位置，依次上浮调整，其实这应该是实现优先队列时的入队操作 **/

		/** 从最后一个非叶子节点开始进行下沉调整，叶子节点无需下沉 **/
		/** 假设父节点的数组下标为 n
		 *  那么其左儿子的下标为 2n + 1
		 *  右儿子下标为 2n + 2
		 *  那么从后往前数，第一个非叶子节点，肯定是最后一个叶子节点的父节点
		 *  最后一个叶子节点下标为   arr.length - 1
		 *  那么其父节点的下标为   (arr.length - 2) / 2
		 * **/
		for (int i = (arr.length - 2) / 2; i >= 0; i--){
			adjustDown(arr,i,arr.length - 1);
		}
	}

	/** 对某个节点进行下沉 **/
	public void adjustDown(int[] arr,int target,int last){
		int value = arr[target];
		int cur = target;
		while (cur <= last){
			int leftSon = cur * 2 + 1;
			int rightSon = leftSon + 1;
			int max;
			if (rightSon <= last)
				max = arr[leftSon] > arr[rightSon] ? leftSon : rightSon;
			else if (leftSon <= last)
				max = leftSon;
			else
				break;
			if (arr[max] > value){
				arr[cur] = arr[max];
				cur = max;
			}else
				break;
		}
		arr[cur] = value;
	}

	/** 对某个节点，进行上浮 **/
	public void adjustUp(int[] arr,int target){
		int cur = target;
		int value = arr[target];
		while (cur > 0){
			int parent = (cur - 1) / 2;
			if (arr[parent] > value){
				arr[cur] = arr[parent];
				cur = parent;
			}else
				break;
		}
		arr[cur] = value;
	}
}
