package leetcode;

import org.junit.Test;

/**
 * 典型的动态规划问题
 * 或者背包问题
 * **/
public class GoldProblem {
	/**
	 * w 是工人数量
	 * p 是金矿需要的工人数量
	 * g 是金矿的收益
	 * n 是当前剩余的金矿数
	 * **/

	/**
	 * 这样，每次将问题分解成2个最优子结构去求解，一直划分到问题边界
	 * 不过，这样会导致对一些子结构的重复计算
	 * */
	public int maxStrategy(int w,int[] p,int[] g,int n){
		if (w == 0 || n == 0)
			return 0;
		if (w < p[ n - 1])
			return maxStrategy(w,p,g,n - 1);
		return Math.max(maxStrategy(w,p,g,n-1),maxStrategy(w - p[n - 1],p,g,n-1) + g[n - 1]);
	}

	/**
	 * 自顶向上的求解方式，时间复杂度为 O(nw)
	 * 其实还可以优化，因为每次计算某一行的数据，只需要用到上一行的数据，所以并不需要n行，只需要1行
	 * **/
	public int maxStrategyV2(int w,int[] p,int[] g,int n){
		int[][] result = new int[n + 1][w + 1];
		for (int i = 0; i < n ; i++){
			for (int j = 1; j <= w; j++){
				if (j < p[i])
					result[i + 1][j] = result[i][j];
				else {
					result[i + 1][j] = Math.max(result[i][j],g[i] + result[i][j - p[i]]);
				}
			}
		}
		return result[n][w];
	}

	/**
	 * 内存复杂度优化
	 * **/
	public int maxStrategyV3(int w,int[] p,int[] g,int n){
		int[] result = new int[w + 1];
		for (int i = 0; i < n; i++){
			int[] temp = new int[w + 1];
			for (int j = 1; j <= w; j++){
				if (j < p[i])
					temp[j] = result[j];
				else
					temp[j] = Math.max(result[j],g[i] + result[j - p[i]]);
			}
			System.arraycopy(temp,0,result,0,w + 1);
		}
		return result[w];
	}

	@Test
	public void test(){
		int[] p = {3,4,3,5,5};
		int[] g = {200,300,350,400,500};
		int w = 10;
		int n = 5;
		int res = maxStrategyV3(w,p,g,n);
		System.out.println(res);
	}
}
