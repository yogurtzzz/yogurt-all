package leetcode;

import org.junit.Test;
import yogurt.data_structure.list.ArrayList;
import yogurt.data_structure.list.List;

/** 一个整数，求从中删除k个数后，最小值 **/
public class MinNumber {
	public int minNumAfterKDeletion(int num,int k){
		ArrayList<Integer> arr = new ArrayList<>();
		while (num > 0){
			if (arr.size() == 0)
				arr.add(num%10);
			else
				arr.add(0,num%10);
			num /= 10;
		}
		while (k > 0){
			int size = arr.size();
			if (size == 0)
				throw new RuntimeException("k超出了数字总位数");
			for (int i = 0; i < size; i++){
				if (i == arr.size() - 1){
					arr.remove(i);
					break;
				}
				if (arr.get(i) > arr.get(i + 1)){
					arr.remove(i);
					break;
				}
			}
			k--;
		}
		Integer[] resArr = arr.toArray(new Integer[0]);
		int res = 0;
		int base = 1;
		for (int i = resArr.length - 1; i >= 0; i--){
			res += resArr[i] * base;
			base *= 10;
		}
		return res;
	}

	@Test
	public void test(){
		System.out.println(minNumAfterKDeletion(1234,3));
		System.out.println(minNumAfterKDeletion(789132189,1));
	}
}
