package sort.core;

import org.junit.Test;
import utils.ArrayUtil;

import java.util.Arrays;

public class BubbleSort {
	public void sort(int[] arr){
		for (int i = arr.length - 1; i >= 0; i--){
			for (int j = 0; j < i; j++){
				if (arr[j] > arr[j + 1]){
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * 若某一趟排序，没有进行任何元素交换，表明已有序
	 */
	public void sortV2(int[] arr){
		for (int i = arr.length - 1; i >= 0; i--){
			boolean isSorted = true;
			for (int j = 0; j < i; j++){
				if (arr[j] > arr[j + 1]){
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					isSorted = false;
				}
			}
			if (isSorted)
				break;
		}
	}

	/**
	 * 每躺排序，记录有序子序列长度，确定下一趟排序的终点
	 */
	public void sortV3(int[] arr){
		int sortedSize = 0;
		while (true){
			boolean isSorted = true;
			int lastSwapPos = 0;
			for (int j = 0; j < arr.length - 1 - sortedSize; j++){
				if (arr[j] > arr[j + 1]){
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
					isSorted = false;
					lastSwapPos = j;
				}
			}
			if (isSorted)
				break;
			sortedSize = arr.length - 1 - lastSwapPos;
		}
	}

	/**
	 * 冒泡排序升级版 - 鸡尾酒排序
	 * 用于优化像   2 3 4 5 6 7 8 1 9  这样的序列
	 * 采用双向冒泡，奇数轮向右冒泡，偶数轮向左冒泡
	 * @param arr
	 */
	public void cockTailSort(int[] arr){
		int leftBorder = 0,rightBorder = arr.length - 1;
		boolean toRight = true;
		while (true){
			boolean isSorted = true;
			if (toRight){
				int lastSwapPos = leftBorder;
				for (int i = leftBorder; i < rightBorder; i++){
					if (arr[i] > arr[i + 1]){
						int temp = arr[i];
						arr[i] = arr[i + 1];
						arr[i + 1] = temp;
						isSorted = false;
						lastSwapPos = i;
					}
				}
				rightBorder = lastSwapPos;
				toRight = false;
			}else {
				int lastSwapPos = rightBorder;
				for (int i = rightBorder; i > leftBorder ;i--){
					if (arr[i] < arr[i - 1]){
						int temp = arr[i];
						arr[i] = arr[i - 1];
						arr[i - 1] = temp;
						isSorted = false;
						lastSwapPos = i;
					}
				}
				leftBorder = lastSwapPos;
				toRight = true;
			}
			if (isSorted)
				break;
		}
	}
	@Test
	public void test(){
		int[] a1 = ArrayUtil.createRandomArray(-200, 3000, 1000);
		int[] a2 = Arrays.copyOf(a1, a1.length);
		int[] a3 = Arrays.copyOf(a1, a1.length);
		int[] a4 = Arrays.copyOf(a1, a1.length);

		long t1 = System.nanoTime();
		sort(a1);
		long t2 = System.nanoTime();
		sortV2(a2);
		long t3 = System.nanoTime();
		sortV3(a3);
		long t4 = System.nanoTime();
		cockTailSort(a4);
		long t5 = System.nanoTime();

		ArrayUtil.assertSorted(a1);
		ArrayUtil.assertSorted(a2);
		ArrayUtil.assertSorted(a3);
		ArrayUtil.assertSorted(a4);

		System.out.println((t2 - t1) / 1000 + "ms");
		System.out.println((t3 - t2) / 1000 + "ms");
		System.out.println((t4 - t3) / 1000 + "ms");
		System.out.println((t5 - t4) / 1000 + "ms");
	}
}
