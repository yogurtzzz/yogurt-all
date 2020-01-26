package leetcode;

import org.junit.Test;

/**
 * 判断一个数是否是2的整数次幂
 * **/
public class PowOfTwo {

	/** O(logn) **/
	public boolean isPowOfTwoSimple(int num){
		int base = 1;
		while (base <= num){
			if (base == num)
				return true;
			base *= 2;
		}
		return false;
	}

	/** O(1) **/
	public boolean isPowOfTwo(int num){
		return (num & (num - 1)) == 0;
	}

	@Test
	public void test(){
		System.out.println(isPowOfTwo(1043));
	}
}
