package leetcode;

import org.junit.Test;

import static utils.Assert.assertTrue;

/** 大数相加 **/
public class BigNumberAddition {
	public String add(String num1,String num2){
		int size1 = num1.length();
		int size2 = num2.length();
		if (size1 == 0)
			return num2;
		if (size2 == 0)
			return num1;
		int i = size1 - 1,j = size2 - 1;
		/** 多出一位用来存进位 **/
		char[] res = new char[size1 > size2 ? size1 + 1 : size2 + 1];
		int k = res.length - 1;
		int carrier = 0;
		while (i >= 0 && j >= 0){
			assertTrue(Character.isDigit(num1.charAt(i)));
			assertTrue(Character.isDigit(num2.charAt(j)));
			int sum = num1.charAt(i) - '0' + num2.charAt(j) - '0' + carrier;
			carrier = sum / 10;
			res[k] = (char)(sum % 10 + '0');
			i--;
			j--;
			k--;
		}
		while (i >= 0){
			int sum = num1.charAt(i) - '0' + carrier;
			carrier = sum / 10;
			res[k] = (char)(sum % 10 + '0');
			i--;
			k--;
		}
		while (j >= 0){
			int sum = num2.charAt(j) - '0' + carrier;
			carrier = sum / 10;
			res[k] = (char)(sum % 10 + '0');
			j--;
			k--;
		}
		return new String(res);
	}

	@Test
	public void test(){
		String num1 = "952594921656516522132165136";
		String num2 = "999755532436";
		System.out.println(add(num1,num2));
	}
}
