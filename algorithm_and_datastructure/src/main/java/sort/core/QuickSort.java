package sort.core;


import org.junit.Test;
import utils.ArrayUtil;
import yogurt.data_structure.base.Pair;
import yogurt.data_structure.stack.Stack;

public class QuickSort {
	//基于分治法，重点在于基准点的选举

	//选取第一个元素作为基准,采用双边循环

	/** 原先，我自己的实现，仅知道一个双边循环 **/
	private void quickSort(int[] arr, int left,int right){
		if (left >= right)
			return;
		int pivot = arr[left];
		int pivotPos = left;
		boolean moveRight = true;
		int posLeft = left + 1,posRight = right;
		while (posLeft <= posRight){
			if (moveRight){
				if (arr[posRight] < pivot){
					arr[pivotPos] = arr[posRight];
					arr[posRight] = pivot;
					pivotPos = posRight;
					moveRight = false;
				}
				posRight--;
			}else {
				if (arr[posLeft] > pivot){
					arr[pivotPos] = arr[posLeft];
					arr[posLeft] = pivot;
					pivotPos = posLeft;
					moveRight = true;
				}
				posLeft++;
			}
		}
		quickSort(arr,left,pivotPos - 1);
		quickSort(arr,pivotPos + 1,right);
	}

	/** 双边循环，比上面简化一些 **/
	public void quickSortDual(int[] arr,int left,int right){
		if (left >= right)
			return;
		int pivot = arr[left];
		int posLeft = left + 1;
		int posRight = right;
		boolean moveRight = true;
		while (posLeft <= posRight){
			if (moveRight){
				if (arr[posRight] < pivot)
					moveRight = false;
				else
					posRight--;

			}else{
				if (arr[posLeft] > pivot){
					int temp = arr[posLeft];
					arr[posLeft] = arr[posRight];
					arr[posRight] = temp;
					moveRight = true;
					posRight--;
				}
				posLeft++;
			}
		}
		/** 最后是posRight指向最后一个小于pivot的元素 **/
		arr[left] = arr[posRight];
		arr[posRight] = pivot;
		quickSortDual(arr,left,posRight - 1);
		quickSortDual(arr,posRight + 1,right);
	}

	/** 单边循环 **/
	public void quickSortUni(int[] arr,int left,int right){
		if (left >= right)
			return;
		int pivot = arr[left];
		int posLeft = left + 1;
		int mark = left;
		while (posLeft <= right){
			if (arr[posLeft] < pivot){
				mark++;
				int temp = arr[mark];
				arr[mark] = arr[posLeft];
				arr[posLeft] = temp;
			}
			posLeft++;
		}
		arr[left] = arr[mark];
		arr[mark] = pivot;
		quickSortUni(arr,left,mark - 1);
		quickSortUni(arr,mark + 1,right);
	}

	/** 用栈实现的非递归版 快排，这时可以将分区的部分代码单独写成一个partition函数了 **/
	public void quickSortWithStack(int[] arr,int left,int right){
		Stack<Pair<Integer, Integer>> stack = new Stack<>();
		stack.push(new Pair<>(left,right));

		while (!stack.isEmpty()){
			Pair<Integer,Integer> pair = stack.pop();
			int start = pair.getLeft();
			int end = pair.getRight();
			if (start >= end)
				continue;
			/**下面尝试将partition函数写在这里，不单独另起函数了**/
			int pivot = arr[start];
			int mark = start;
			int posLeft = start + 1;
			while (posLeft <= end){
				if (arr[posLeft] < pivot){
					mark++;
					int t = arr[mark];
					arr[mark] = arr[posLeft];
					arr[posLeft] = t;
				}
				posLeft++;
			}
			arr[start] = arr[mark];
			arr[mark] = pivot;
			/****/
			stack.push(new Pair<>(mark + 1,end));
			stack.push(new Pair<>(start,mark - 1));
		}
	}

	@Test
	public void test() {
		int[] arr = ArrayUtil.createRandomArray(-100,200,50);
		quickSortUni(arr,0,arr.length - 1);
		ArrayUtil.printArray(arr);
		ArrayUtil.assertSorted(arr);
	}
}
