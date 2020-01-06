package utils;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Non Concurrent Safe
 * @param <T>
 */
public class MyStack<T>{
	private Deque<T> stack = new LinkedList<>();
	private int size = 0;
	public T pop(){
		T ele = stack.pollFirst();
		if (ele != null)
			size--;
		return ele;
	}

	public void push(T e){
		stack.addFirst(e);
		size++;
	}

	public T peek(){
		T ele = stack.peekFirst();
		return ele;
	}
	public int getSize(){
		return this.size;
	}
	public boolean isEmpty(){
		return size == 0;
	}
}
