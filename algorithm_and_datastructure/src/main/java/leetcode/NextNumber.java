package leetcode;

/** 找出所有全排列中，比当前排列大的第一个数 **/

import org.junit.Test;

/**
 * 如12345  , 第一个比它大的则是 12354
 * 思路：一个序列，全排列最大的数，是逆序排列，最小的数是顺序排列
 * 从右侧开始，找出逆序序列的边界，将逆序序列中，比边界前一个数大的，和边界前一个数交换
 * 并将原逆序区间，按照升序排列
 *
 * 1. 从后往前查看逆序区域，找到逆序区域的前一位，也就是数字置换的边界
 * 2. 让逆序区域的前一位和逆序区域中大于它的最小值，做交换
 * 3. 把原来的逆序区域，转为顺序状态
 * **/
public class NextNumber {
	public int nextNumber(int num){
		int temp = num;
		int size = 0;
		while (temp != 0){
			temp /= 10;
			size++;
		}
		int[] arr = new int[size];
		/** 找出逆序序列的左边界 **/
		for (int i = size - 1; i >= 0; i--){
			arr[i] = num % 10;
			num /= 10;
		}
		int border = size - 1;
		for (int i = size - 1;i > 0; i--){
			if (arr[i] <= arr[i - 1]){
				border = i - 1;
				continue;
			}else {
				break;
			}
		}
		if (border == 0)
			throw new RuntimeException("Not Exist");
		int swapPos = border - 1;
		for (int i = size - 1; i >= border; i--){
			if (arr[i] > arr[swapPos]){
				int t = arr[i];
				arr[i] = arr[swapPos];
				arr[swapPos] = t;
				break;
			}
		}
		/** 对左侧逆序进行排列，按升序排列，排成最小 **/
		/** 采用冒泡排序 **/
		for (int i = size - 1; i >= border; i--){
			boolean isSorted = true;
			for (int j = border;j < i ; j++){
				if (arr[j] > arr[j + 1]){
					int t = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = t;
					isSorted = false;
				}
			}
			if (isSorted)
				break;
		}
		int res = 0;
		int base = 1;
		for (int i = size - 1; i >= 0; i--){
			res += arr[i] * base;
			base *= 10;
		}
		return res;
	}

	@Test
	public void test(){
		System.out.println(nextNumber(1246598794));
	}
}
