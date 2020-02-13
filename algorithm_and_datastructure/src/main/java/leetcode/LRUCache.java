package leetcode;

import java.util.HashMap;

public class LRUCache<K,V> {
	static class Node<K,V>{
		K key;
		V value;
		Node pre;
		Node next;
		public Node(K key,V value){
			this.key = key;
			this.value = value;
		}
	}

	private int size;
	private HashMap<K,Node> map;
	private Node head;
	private Node tail;

	public LRUCache(int size){
		this.size = size;
		this.map = new HashMap<>();
	}

	public V get(K key){
		Node<K,V> node = map.get(key);
		if (node == null)
			return null;
		refreshNode(node);
		return node.value;
	}

	public void put(K key,V value){
		Node<K,V> node = map.get(key);
		if (node == null){
			if (map.size() >= size){
				Object oldKey = head.key;
				removeNode(head);
				map.remove(oldKey);
			}
			node = new Node<>(key,value);
			map.put(key,node);
			addNode(node);
		}else {
			node.value = value;
			refreshNode(node);
		}
	}

	public void printAll(){
		Node c = head;
		while (c != null){
			System.out.println(c.key + ":" + c.value);
			c = c.next;
		}
	}

	private void refreshNode(Node node){
		if (tail == node)
			return;
		removeNode(node);
		addNode(node);
	}

	private void addNode(Node node){
		if (tail == null){
			head = node;
		}else {
			tail.next = node;
			node.pre = tail;
		}
		tail = node;
	}
	private void removeNode(Node node){
		if (head == tail && tail == node)
			head = tail = null;
		else if (tail == node){
			Node t = tail;
			tail = tail.pre;
			tail.next = null;
			t.pre = null;
		}else if (head == node){
			Node t = head;
			head = head.next;
			head.pre = null;
			t.next = null;
		}else {
			Node pre = node.pre;
			Node next = node.next;
			node.pre = node.next = null;
			pre.next = next;
			next.pre = pre;
		}
	}


	public static void main(String[] args) {
		LRUCache<String,Integer> cache = new LRUCache<>(3);
		cache.put("yogurt",234);
		cache.put("amber",41233);
		Integer yogurt = cache.get("yogurt");
		System.out.println(yogurt);

		cache.put("Cicy",12123);

		cache.printAll();
	}
}
