package leetcode;

import org.junit.Test;

import java.util.HashSet;

public class ListWithCircle {
	static class Node{
		int val;
		Node next;
		public Node(int val){
			this.val = val;
			this.next = null;
		}
	}

	/** 最笨的方法，穷举遍历 **/
	public boolean hasCircleV1(Node head){
		int pos = 0;
		Node cur = head;
		while (cur != null){
			Node curAgin = head;
			for(int i = 0; i < pos; i++){
				if (curAgin == cur)
					return true;
				curAgin = curAgin.next;
			}
			cur = cur.next;
			pos++;
		}
		return false;
	}

	/** hash表做缓存 **/
	public boolean hasCircleV2(Node head){
		HashSet<Node> temp = new HashSet<>();
		Node cur = head;
		while (cur != null){
			if (temp.contains(cur))
				return true;
			temp.add(cur);
			cur = cur.next;
		}
		return false;
	}
	/** 双指针追及法 **/
	public boolean hasCircleV3(Node head){
		Node fast,slow;
		fast = slow = head;
		while (fast != null && slow != null){
			fast = fast.next.next;
			slow = slow.next;
			if (fast == slow)
				return true;
		}
		return false;
	}

	public int circleLength(Node head){
		Node fast,slow;
		fast = slow = head;
		boolean firstMeet = false;
		int len = 0;
		while (fast != null && slow != null){
			fast = fast.next.next;
			slow = slow.next;
			if (firstMeet){
				len++;
				if (fast == slow)
					break;
			}
			if (fast == slow && !firstMeet)
				firstMeet = true;
		}
		return len;
	}
	public Node firstNodeInCircle(Node head){
		Node fast,slow;
		fast = slow = head;
		boolean firstMeet = false;
		while (fast != null && slow != null){
			fast = fast.next.next;
			slow = slow.next;
			if (fast == slow && !firstMeet){
				firstMeet = true;
				break;
			}
		}
		if (!firstMeet)
			return null;
		fast = head;
		while (fast != slow){
			fast = fast.next;
			slow = slow.next;
		}
		return fast;
	}

	@Test
	public void testCircle(){
		Node n1 = new Node(2);
		Node n2 = new Node(4);
		Node n3 = new Node(1);
		Node n4 = new Node(5);
		Node n5 = new Node(8);
		Node n6 = new Node(0);

		n1.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = n4;

		Node head = n1;
		System.out.println(hasCircleV1(head));
		System.out.println(hasCircleV2(head));
		System.out.println(hasCircleV3(head));
		System.out.println(circleLength(head));
		System.out.println(firstNodeInCircle(head).val);
	}

}
