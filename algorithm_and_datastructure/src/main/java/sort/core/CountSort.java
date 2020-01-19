package sort.core;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.junit.Test;
import utils.ArrayUtil;

import java.util.Arrays;
import java.util.function.IntFunction;

import static utils.ArrayUtil.printArray;

/** Not based on compare **/

/***
 * 适用于一定范围内的整数排序，在取值范围不大的情况下，性能比较好
 * 局限：
 *      不适用于小数
 *      最大值与最小值差距过大时，也不适用
 *
 * 线性时间复杂度
 */
public class CountSort {
	public void countSort(int[] arr){
		if (arr == null || arr.length <= 1)
			return ;
		int min,max;
		min = max = arr[0];
		for (int i = 1; i < arr.length; i++){
			if (arr[i] < min)
				min = arr[i];
			if (arr[i] > max)
				max = arr[i];
		}
		int[] count = new int[max - min + 1];
		for (int i = 0; i < arr.length; i++){
			count[arr[i] - min]++;
		}

		int k = 0;
		for (int i = 0; i < max - min + 1; i++){
			int cnt = count[i];
			while (cnt > 0){
				arr[k++] = i + min;
				cnt--;
			}
		}
	}

	/**
	 * 优化，相同元素可以区分
	 * 从第二个元素开始，每个元素要加上前面所有元素的和
	 * **/


	@Setter
	@Getter
	@AllArgsConstructor
	static class Student{
		private String name;
		private int score;

		@Override
		public String toString() {
			return "Student{" +
					"name='" + name + '\'' +
					", score=" + score +
					'}'+'\n';
		}
	}
	/** 可以保证稳定 **/
	/** 时间复杂度为 3n + m , 为 O(n + m) **/
	/** n为原始数组长度，m为 max - min + 1 **/
	/** 空间复杂度（不考虑结果数组）：为 O(m) **/
	public Student[] countSortEnhanced(Student[] arr){
		if (arr == null || arr.length <= 1)
			return arr;
		int min,max;
		min = max = arr[0].getScore();
		for (int i = 1; i < arr.length ; i++){
			if (arr[i].getScore() < min)
				min = arr[i].getScore();
			if (arr[i].getScore() > max)
				max = arr[i].getScore();
		}
		/** 计数 **/
		int[] scoreCnt = new int[max - min + 1];
		for (int i = 0; i < arr.length; i++){
			scoreCnt[arr[i].getScore() - min]++;
		}
		/** 变形 **/
		for (int i = 0; i < max - min + 1; i++){
			if (i == 0)
				continue;
			scoreCnt[i] += scoreCnt[i - 1];
		}
		/**对原数组从后往前遍历**/
		Student[] sortedArr = new Student[arr.length];
		for (int i = arr.length - 1; i >= 0; i--){
			/** 注意排名第一，要插入到第0号位置，所以用前置--，而不是后置-- **/
			int pos = --scoreCnt[arr[i].getScore() - min];
			sortedArr[pos] = arr[i];
		}
		return sortedArr;
	}

	@Test
	public void test2(){
		Student[] arr = Arrays.asList(
				new Student("yogurt",95),
				new Student("amber",94),
				new Student("lily",96),
				new Student("potter",99),
				new Student("harry",94),
				new Student("tom",99)
		).stream().toArray(Student[]::new);

		Student[] sorted = countSortEnhanced(arr);

		printArray(sorted);
	}

	@Test
	public void test(){
		int[] arr = {2,-8,5,12,1,3,4,9,10,5,7,6};
		countSort(arr);
		printArray(arr);
	}
}
