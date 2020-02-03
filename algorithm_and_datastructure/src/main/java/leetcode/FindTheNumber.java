package leetcode;

import org.junit.Test;

/**
 * 寻找缺失的整数
 * **/
public class FindTheNumber {

	/**
	 * 数组arr，共有99个不重复的元素，范围是1-100，找出1-100中缺失的那个整数
	 * **/
	public int findTheMissingNumber(int[] arr){
		int sum = (1 + 100) * 50;
		for (int i = 0;i < arr.length ;i++)
			sum -= arr[i];
		return sum;
	}


	/**
	 * 数组arr中，有1个数出现了奇数次，其他数都出现了偶数次
	 * 用异或的方式来求得这个出现了奇数次的数
	 * **/
	public int findTheNumberOccursOddTimes(int[] arr){
		int res = 0;
		for (int i = 0; i < arr.length; i++){
			res = res ^ arr[i];
		}
		return res;
	}

	@Test
	public void test2(){
		int[] arr = new int[]{1,1,8,8,8,8,5,5,4,4,9};
		System.out.println(findTheNumberOccursOddTimes(arr));
	}
	@Test
	public void test(){
		int[] arr = new int[99];
		int k = 0;
		for (int i = 1; i <= 100; i++){
			if (i == 53)
				continue;
			else
				arr[k++] = i;
		}
		System.out.println(findTheMissingNumber(arr));
	}
}
