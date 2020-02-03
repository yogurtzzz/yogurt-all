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

	/**
	 * 若有2个数出现了奇数次，找出这2个数
	 * 先所有数做异或，然后得到的结果保留了2个数不同位的特点
	 * 从低到高，找出第一个出现不同的位
	 * 然后从头遍历数组，将这个位为0，和为1的数，分成2组，分别各自做异或
	 * 出现奇数次的2个数，一定没有被分在同一组
	 * 所以左右两组，就转换成了上述，只有1个数出现奇数次的情况
	 * **/
	public int[] findTheTwoNumberOccursOddTimes(int[] arr){
		int xor = 0;
		for (int i = 0; i < arr.length; i++){
			xor = xor ^ arr[i];
		}
		if (xor == 0)
			return null;
		int diffBit = 1;
		while (0 == (xor & diffBit)){
			diffBit <<= 1;
		}
		int[] res = new int[2];
		for (int i = 0; i < arr.length; i++){
			if (0 == (arr[i] & diffBit))
				res[0] ^= arr[i];
			else
				res[1] ^= arr[i];
		}
		return res;
	}

	@Test
	public void test3(){
		int[] arr = new int[]{1,1,3,3,5,5,4,4,4,9};
		int[] res = findTheTwoNumberOccursOddTimes(arr);
		for (int i : res){
			System.out.println(i);
		}
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
