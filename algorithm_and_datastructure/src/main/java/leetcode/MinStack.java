package leetcode;

import yogurt.data_structure.list.ArrayList;
import yogurt.data_structure.list.List;

import java.util.Arrays;

/** 实现一个最小栈，有 pop , push , 以及 求 min 三个方法 **/
public class MinStack<T extends Comparable> {
	private List<T> element;
	private List<Integer> temp;
	private int minPos;

	public MinStack(){
		element = new ArrayList<>();
		temp = new ArrayList<>();
		minPos = -1;
	}

	public void push(T t){
		element.add(t);
		if (minPos == -1){
			minPos = 0;
			temp.add(0);
		}else{
			T min = element.get(minPos);
			if (t.compareTo(min) < 0){
				minPos = element.size() - 1;
				temp.add(minPos);
			}
		}
	}

	public T pop(){
		T t = element.get(element.size() - 1);
		if (minPos == element.size() - 1){
			temp.remove(temp.size() - 1);
			minPos = temp.get(temp.size() - 1);
		}
		element.remove(element.size() - 1);
		return t;
	}
	public T min(){
		T min = element.get(minPos);
		return min;
	}

	public static void main(String[] args) {
		MinStack<Integer> stack = new MinStack<>();
		stack.push(2);
		stack.push(5);
		stack.push(-3);
		stack.push(1);
		stack.push(4);
		stack.push(0);
		stack.push(7);
		stack.push(8);
		stack.push(-4);
		stack.push(-5);

		System.out.println(stack.min());
		System.out.println(stack.pop());
		System.out.println(stack.min());
		System.out.println(stack.pop());
		System.out.println(stack.min());
		System.out.println(stack.pop());
	}
}
