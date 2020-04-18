package leetcode;

import java.util.HashMap;

/**
 * leetcode 146题
 * **/
public class LRUCache{
	static class Node{
		int key;
		int value;
		Node prev;
		Node next;
	}

	private HashMap<Integer,Node> map;

	private Node head;

	private Node tail;

	private int capacity;


	public LRUCache(int capacity) {
		map = new HashMap<>(capacity);
		this.capacity = capacity;
	}

	public int get(int key) {
		Node node = map.get(key);
		refreshNode(node);
		return node == null ? -1 : node.value;
	}

	public void put(int key, int value) {
		Node oldNode = map.get(key);
		if (oldNode != null){
			oldNode.value = value;
			refreshNode(oldNode);
			return ;
		}

		if (map.size() == capacity){
			int oldestKey = head.key;
			map.remove(oldestKey);
			Node newHead = head.next;
			head.next = null;
			if (newHead != null){
				newHead.prev = null;
			}
			head = newHead;
			if (head == null){
				tail = null;
			}
		}
		Node newNode = new Node();
		newNode.value = value;
		newNode.key = key;
		map.put(key,newNode);
		if (head == null){
			head = tail = newNode;
		}else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
	}

	private void refreshNode(Node node){
		if (node == null){
			return;
		}
		Node prev = node.prev;
		Node next = node.next;
		if (prev == null && next == null)
			return;
		if (prev == null){
			/** 该Node是head **/
			head = node.next;
			head.prev = null;
			node.next = null;
			tail.next = node;
			node.prev = tail;
			tail = node;
		}else if (next == null){
			/** 该Node是tail **/

		}else {
			prev.next = next;
			next.prev = prev;
			tail.next = node;
			node.prev = tail;
			node.next = null;
			tail = node;
		}
	}
}
