package leetcode;

import org.junit.Test;

/** 求解最大公约数 **/
public class MaxCommonDivider {
	/** 辗转相除法 **/
	/** 当两数较大时，模运算会比较耗时 **/
	public int gcd(int a, int b){
		int max = a > b ? a : b;
		int min = a > b ? b : a;
		while (min > 0){
			int s = max % min;
			max = min;
			min = s;
		}
		return max;
	}

	/** 更相减损术 **/
	/** 两数相差悬殊时，运算次数太多 **/
	public int gcd2(int a, int b){
		int max = a > b ? a : b;
		int min = a > b ? b : a;
		while (min > 0){
			int s = max - min;
			max = min > s ? min : s;
			min = min > s ? s : min;
		}
		return max;
	}

	/** 结合辗转相除和更相减损 **/
	/**
	 *  当a 和 b 都是偶数时， gcd(a,b) = 2 * gcd(a/2,b/2)
	 *  当一个奇数一个偶数时， gcd(a,b) = gcd(偶数/2,奇数)
	 *  当都是奇数时，使用一次更相减损术，结果肯定是1奇1偶
	 * **/
	public int gcd3(int a, int b){
		if (a == b)
			return a;
		else if ((a&1) == 0 && (b&1) == 0)
			return gcd3(a>>1,b>>1)<<1;
		else if ((a&1) == 0 && (b&1) != 0)
			return gcd3(a>>1,b);
		else if ((a&1) != 0 && (b&1) == 0)
			return gcd3(a,b>>1);
		else{
			int max = a > b ? a : b;
			int min = a > b ? b : a;
			return gcd3(min,max-min);
		}

	}


	@Test
	public void test(){
		int i = gcd3(54,45);
		System.out.println(i);
	}
}
