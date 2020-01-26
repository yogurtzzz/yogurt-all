package leetcode;


import org.junit.Test;


/** 无序数组排序后的最大相邻差
 *  用计数排序的思想
 * **/
public class MaxAdjacentDiff {
	/** 最大相邻差 **/
	/** 计数排序的思想 **/
	/** 局限性: 若有相邻的数相差太大，则会浪费很多空间 **/
	public int maxDiff(int[] arr){
		if (arr == null || arr.length <= 1)
			return 0;
		int max,min;
		max = min = arr[0];
		for (int i = 1; i < arr.length; i++){
			if (arr[i] > max)
				max = arr[i];
			if (arr[i] < min)
				min = arr[i];
		}
		if (max == min)
			return 0;
		int[] count = new int[max - min + 1];
		for (int i = 0; i < arr.length; i++){
			count[arr[i] - min]++;
		}
		int countZero = 0;
		int maxDiff = 0;
		for (int i = 0; i < max - min + 1; i++){
			if (count[i] != 0){
				maxDiff = countZero + 1;
				countZero = 0;
			}else {
				countZero++;
			}
		}
		return maxDiff;
	}

	/** 桶排 **/
	public int maxDiffBucket(int[] arr){
		if (arr == null ||arr.length <= 1)
			return 0;
		int max,min;
		max = min = arr[0];
		for (int i = 0; i < arr.length; i++){
			if (arr[i] > max)
				max = arr[i];
			if (arr[i] < min)
				min = arr[i];
		}
		if (max == min)
			return 0;
		Bucket[] buckets = new Bucket[arr.length];
		for (int i = 0; i < arr.length; i++)
			buckets[i] = new Bucket();
		int d = max - min;
		for (int i = 0; i < arr.length; i++){
			int pos = (arr[i] - min) * (arr.length - 1) / d;
			if (buckets[pos].min == null || buckets[pos].min > arr[i])
				buckets[pos].min = arr[i];
			if (buckets[pos].max == null || buckets[pos].max < arr[i])
				buckets[pos].max = arr[i];
		}

		int maxDiff = 0;
		boolean moveLeft = true,moveRight = true;
		Integer leftMax = null,rightMin = null;
		for (int i = 0; i < arr.length; i++){
			if (leftMax == null && rightMin == null){
				leftMax = buckets[i].max;
			}
			if (leftMax != null){
				rightMin = buckets[i].min;
				if (rightMin != null){
					int diff = rightMin - leftMax;
					if (diff > maxDiff)
						maxDiff = diff;
					leftMax = buckets[i].max;
					rightMin = null;
				}
			}

		}
		return maxDiff;
	}
	static class Bucket{
		Integer min;
		Integer max;
	}

	@Test
	public void test(){
		int[] arr = {1,2,-10,5,15};
		int a = maxDiffBucket(arr);
		System.out.println(a);
	}
}
