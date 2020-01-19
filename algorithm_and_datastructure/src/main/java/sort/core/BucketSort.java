package sort.core;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import static utils.ArrayUtil.printArray;

/**
 * 需要若干个桶，每个桶代表一个区间，里面可以承载一个或多个元素
 * 关键在于确定需要建立的桶的个数，以及每个桶的区间范围
 *
 * 在本例中取桶的数量为原始数列的元素数量
 * **/
public class BucketSort {
	public void bucketSort(int[] arr){
		if(arr == null || arr.length <= 1)
			return ;
		int max,min;
		max = min = arr[0];
		for (int i = 1; i < arr.length; i++){
			if (arr[i] > max)
				max = arr[i];
			if (arr[i] < min)
				min = arr[i];
		}

		int d = max - min;
		ArrayList<LinkedList<Integer>> bucket = new ArrayList<>();
		for (int i = 0; i < arr.length; i++){
			bucket.add(new LinkedList<Integer>());
		}

		/** 注意要arr.length - 1 ，不然当到第 max 时， max - min / d = arr.length ，这时会越界 **/
		for (int i = 0; i < arr.length; i++){
			int pos = (arr[i] - min) * (arr.length - 1) / d;
			bucket.get(pos).add(arr[i]);
		}
		for (int i = 0; i < arr.length; i++){
			LinkedList<Integer> integers = bucket.get(i);
			Collections.sort(integers);
		}
		int k = 0;
		for (int i = 0; i < arr.length; i++){
			for (Integer item : bucket.get(i))
				arr[k++] = item;
		}
	}

	@Test
	public void test(){
		int[] arr = {7,-2,5,12,11,9,4,-3,32,24,22,8};
		bucketSort(arr);
		printArray(arr);
	}
}
