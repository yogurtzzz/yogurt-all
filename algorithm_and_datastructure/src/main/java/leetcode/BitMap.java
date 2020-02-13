package leetcode;

import yogurt.data_structure.list.ArrayList;
import yogurt.data_structure.list.List;

import java.util.Arrays;

/**
 * 可以用byte数组实现
 * 也可以用long数组实现
 * 注意移位时的  1 要写成 1L  ，否则会被当作int，移位超过32位会溢出
 * **/
public class BitMap {
	private long[] bitmap;
	private int size;
	public BitMap(int size){
		this.size = size;
		this.bitmap = new long[getIndex(size - 1) + 1];
	}

	public void setBit(int num){
		int index = getIndex(num);
		bitmap[index] |= 1L << getOffset(num);
	}

	public int getBit(int num){
		int index = getIndex(num);
		return (bitmap[index] & 1L << getOffset(num)) != 0 ? 1 : 0;
	}

	public void resetBit(int num){
		int index = getIndex(num);
		bitmap[index] &= ~(1L << getOffset(num));
	}

	public void clear(){
		Arrays.fill(bitmap,0);
	}

	public List<Integer> getAllExisted(){
		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < size; i++){
			if (getBit(i) == 1)
				result.add(i);
		}
		return result;
	}

	private int getIndex(int num){
		return num >> 6;
	}

	private int getOffset(int num){
		return num % 64;
	}



	public static void main(String[] args){
		BitMap bitMap = new BitMap(1000);
		bitMap.setBit(925);
		bitMap.setBit(250);
		bitMap.getAllExisted().forEach(System.out::println);
	}
}
