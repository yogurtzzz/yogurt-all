package leetcode;

import yogurt.data_structure.stack.Stack;

/** 用栈实现的队列 **/
public class QueueByStack<T extends Comparable> {
	private Stack<T> inStack;
	private Stack<T> outStack;
	public QueueByStack(){
		inStack = new Stack<>();
		outStack = new Stack<>();
	}

	/** 入队 **/
	public void push(T e){
		inStack.push(e);
	}
	/** 出队 **/
	public T pop(){
		if (!outStack.isEmpty()){
			return outStack.pop();
		}else {
			while (!inStack.isEmpty()){
				outStack.push(inStack.pop());
			}
			return outStack.pop();
		}
	}
	/** 是否为空 **/
	public boolean isEmpty(){
		return inStack.isEmpty() && outStack.isEmpty();
	}

	public static void main(String[] args) {
		QueueByStack<Integer> queue = new QueueByStack<>();
		queue.push(2);
		queue.push(1);
		queue.push(3);
		queue.push(-5);
		queue.push(7);
		queue.push(13);

		System.out.println(queue.pop());

		queue.push(25);
		queue.push(21);
		System.out.println(queue.pop());
		while (!queue.isEmpty()){
			System.out.println(queue.pop());
		}
	}
}
