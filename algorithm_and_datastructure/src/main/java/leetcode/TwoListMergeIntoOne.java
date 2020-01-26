package leetcode;

import org.junit.Test;

/**
 * 以下算法会改变原有链表的变量引用
 */
public class TwoListMergeIntoOne {
	static class Node{
		Integer value;
		Node next;
		public Node(Integer value){
			this.value = value;
			this.next = null;
		}
	}

	/**
	 * 将2个升序单链表，合并成1个降序单链表
	 */
	public static Node merge(Node one,Node two){
		if (one == null && two == null)
			return null;
		if (one == null){
			//one为null,只需要反转two
			return reverse(two);
		}
		else if (two == null){
			//two为null，只需要反转one
			return reverse(one);
		}
		//都不为null，进行合并
		Node cursorOne = one,cursorTwo = two;
		//prev 存储上一次操作的节点
		//后比较 链表1 链表2，取出最小的节点 min ，然后使 min->next = prev
		//首次，取出最小的节点，后开始循环
		Node prev;
		if (cursorOne.value <= cursorTwo.value){
			prev = cursorOne;
			cursorOne = cursorOne.next;
		}else{
			prev = cursorTwo;
			cursorTwo = cursorTwo.next;
		}

		//注意将头节点的next设为null，否则出现环
		prev.next = null;

		while(cursorOne != null && cursorTwo != null){
			Node min;
			if (cursorOne.value <= cursorTwo.value){
				min = cursorOne;
				cursorOne = cursorOne.next;
			}else{
				min = cursorTwo;
				cursorTwo = cursorTwo.next;
			}
			//取出最小的，然后指向上一个节点
			min.next = prev;
			prev = min;
		}

		//最后应该有一个链表的节点先取完
		//这里获取未取完节点的那个链表，从还未操作的节点开始

		//反转单链表，要注意保留下一个需节点的信息
		//因为改变上一个节点的next指针时，会丢失下一个节点
		Node remainder = cursorOne != null ? cursorOne : cursorTwo;
		while(remainder != null){
			Node temp = remainder;
			remainder = remainder.next;
			temp.next = prev;
			prev = temp;
		}
		return prev;
	}


	/**
	 * 反转单向链表
	 */
	public static Node reverse(Node node){
		if (node == null)
			return null;
		Node prev = node;
		node = node.next;

		//这里一定要注意把头节点的next赋为null，否则出现环
		prev.next = null;

		while (node != null){
			Node temp = node.next;
			node.next = prev;
			prev = node;
			node = temp;
		}
		return prev;
	}

	/**
	 * 构造链表
	 */
	public static Node constructLinkedList(int ... values){
		if (values == null || values.length == 0)
			return null;
		Node prev = new Node(values[0]);
		Node head = prev;
		for (int i = 1; i < values.length ;i++){
			Node item = new Node(values[i]);
			prev.next = item;
			prev = item;
		}
		return head;
	}

	/**
	 * 遍历链表
	 */
	public static void tranverseList(Node head){
		while (head != null){
			System.out.print(head.value + " ");
			head = head.next;
		}
		System.out.println();
	}

	@Test
	public void test(){
		int[] nodes1 = new int[]{1,3,7,9,12,20};
		int[] nodes2 = new int[]{2,3,5,10,23};
		Node list1 = constructLinkedList(nodes1);
		Node list2 = constructLinkedList(nodes2);
		tranverseList(list1);
		tranverseList(list2);
		Node rev = merge(list1,list2);
		tranverseList(rev);
		tranverseList(list1);
		tranverseList(list2);

//
//		Node revList1 = reverse(list1);
//		Node revList2 = reverse(list2);
//		tranverseList(revList1);
//		tranverseList(revList2);
//		System.out.println("end");
	}
}
