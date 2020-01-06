package sort.core;


import org.junit.Test;
import utils.ArrayUtil;
import utils.MyPair;
import utils.MyStack;

public class QuickSort {
	//基于分治法，重点在于基准点的选举

	//选取第一个元素作为基准,采用双边循环
	public void sort(int[] arr){
		quickSort(arr,0,arr.length - 1);
	}

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


	/** 《算法 小灰》 双边循环 **/
	public void quickSortV1(int[] arr,int left,int right){
		if (left >= right)
			return ;
		/** 其实这里可以写在内部，而不必另起一个新的函数 **/
		int pivotPos = partition(arr,left,right);
		quickSortV1(arr,left,pivotPos - 1);
		quickSortV1(arr,pivotPos + 1,right);
	}
	private int partition(int[] arr,int left,int right){
		int pivot = arr[left];
		int posLeft = left + 1;
		int posRight = right;
		boolean moveRight = true;
		while (posLeft < posRight){
			if (moveRight){
				if (arr[posRight] < pivot){
					moveRight = false;
				}else
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
		int pivotPos = 0;
		if (posRight < posLeft){
			arr[left] = arr[posRight];
			arr[posRight] = pivot;
			pivotPos = posRight;
		}else if (posRight == posLeft){
			if (arr[posLeft] <= pivot){
				arr[left] = arr[posLeft];
				arr[posLeft] = pivot;
				pivotPos = posLeft;
			}else{
				arr[left] = arr[posLeft - 1];
				arr[posLeft - 1] = pivot;
				pivotPos = posLeft - 1;
			}
		}
		return pivotPos;
	}

	/** 《算法 小灰》  单边循环 **/
	public void quickSortV2(int[] arr,int left,int right){
		if (left >= right)
			return;
		int pivotPos = partitionV2(arr,left,right);
		quickSortV2(arr,left,pivotPos - 1);
		quickSortV2(arr,pivotPos + 1,right);
	}
	private int partitionV2(int[] arr,int left,int right){
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
		return mark;
	}

	/** 自实现的单边循环，不另起partition函数 **/
	public void quickSortBest(int[] arr,int left,int right){
		if (left >= right)
			return ;
		int pivot = arr[left];
		int mark = left;
		int posLeft = left + 1;
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
		quickSortBest(arr,left,mark - 1);
		quickSortBest(arr,mark + 1,right);
	}

	@Test
	public void test() {
		int[] arr = ArrayUtil.createRandomArray(-100,200,100);
		quickSortWithStack(arr,0,arr.length - 1);
		ArrayUtil.printArray(arr);
		ArrayUtil.assertSorted(arr);
	}

	/** 用栈实现的非递归版 快排，这时可以将分区的部分代码单独写成一个partition函数了 **/
	public void quickSortWithStack(int[] arr,int left,int right){
		MyStack<MyPair<Integer, Integer>> stack = new MyStack<>();
		stack.push(new MyPair<>(left,right));

		while (!stack.isEmpty()){
			MyPair<Integer,Integer> pair = stack.pop();
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
			stack.push(new MyPair<>(mark + 1,end));
			stack.push(new MyPair<>(start,mark - 1));
		}
	}
}
