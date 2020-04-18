package yogurt.data_structure.map;

import org.omg.CORBA.INTERNAL;

import java.util.Objects;

public class HashMap<K,V> {

	private final int DEFAULT_CAPACITY = 1 << 4;  // aka 16

	private final float DEFAULT_LOAD_FACTOR = 0.75f;

	private final int MAX_CAPACITY = 1 << 30;

	private int threshold;

	private int capacity;

	private float loadFactor;

	private int size;

	private Entry<K,V>[] tab;

	static class Entry<K,V>{

		private int hash;

		private K key;

		private V value;

		private Entry<K,V> next;

		public Entry(int hash, K key, V value) {
			this.hash = hash;
			this.key = key;
			this.value = value;
		}
	}

	public HashMap(){
		this.loadFactor = DEFAULT_LOAD_FACTOR;
	}

	public HashMap(int initialCapacity){
		this.capacity = tableSize(initialCapacity);
		this.loadFactor = DEFAULT_LOAD_FACTOR;
	}

	private int tableSize(int size){

		int n = size - 1;
		n |= n >> 1;
		n |= n >> 2;
		n |= n >> 4;
		n |= n >> 8;
		n |= n >> 16;

		return n <= 0 ? 1 : (n + 1) > MAX_CAPACITY ? MAX_CAPACITY : (n + 1);
	}

	public V get(K key){
		int hash = hash(key);
		int i;
		V value = null;
		if (tab == null)
			return null;
		Entry<K,V> node = tab[i = (hash & capacity - 1)];
		if (node != null) {
			if (node.hash == hash && Objects.equals(node.key,key)){
				value = node.value;
			}else {
				Entry<K,V> n = node;
				while (!(n.hash == hash && Objects.equals(n.key,key))){
					n = n.next;
					if (n == null){
						break;
					}
				}
				value = n == null ? null : n.value;
			}
		}
		return value;
	}

	public V put(K key, V value){
		return putVal(hash(key),key,value);
	}

	private V putVal(int hash,K key,V value){
		V oldValue = null;
		Entry[] t;
		if ((t = tab) == null || t.length == 0){
			resize();
		}
		ensureCapacity(size + 1);
		Entry<K,V> node = new Entry<>(hash,key,value);
		int i;
		if (tab[i = hash & (capacity - 1)] == null){
			tab[i] = node;
		}else {
			Entry<K,V> n = tab[i];
			Entry<K,V> e = null;
			do {
				if (n.hash == node.hash && Objects.equals(n.key,node.key)){
					oldValue = n.value;
					n.value = node.value;
					break;
				}else {
					e = n;
					n = n.next;
				}
			}while (n != null);

			if (e != null){
				e.next = node;
			}
		}
		size++;
		return oldValue;
	}

	private void ensureCapacity(int maxSize){
		if (threshold >= maxSize){
			return;
		}
		resize();
	}

	private void resize(){
		int oldCap = capacity;
		int oldThr = threshold;
		int newCap;
		int newThr;
		if (oldCap == 0){
			newCap = DEFAULT_CAPACITY;
			newThr = (int)(newCap * loadFactor);
		}else if (oldThr == 0){
			newCap = oldCap;
			newThr = (int)(newCap * loadFactor);
		}else {
			newCap = oldCap << 1;
			newThr = oldThr << 1;
		}

		capacity = newCap;
		threshold = newThr;
		Entry<K,V>[] newTab = new Entry[capacity];
		for (int i = 0; i < (tab == null ? 0 : tab.length); i++){
			Entry n = tab[i];
			if (n != null){
				Entry nodeLow = null,nodeHigh = null;
				int lowHash = i,highHash = i + oldCap;
				while (n != null){
					int newHash = n.hash & (capacity - 1);
					if (newHash == i){
						if (nodeLow == null){
							nodeLow = new Entry<>(n.hash,n.key,n.value);
						}else {
							Entry t = nodeLow;
							while (t.next != null){
								t = t.next;
							}
							t.next = new Entry<>(n.hash,n.key,n.value);
						}
					}else {
						highHash = newHash;
						if (nodeHigh == null){
							nodeHigh = new Entry<>(n.hash,n.key,n.value);;
						}else{
							Entry t = nodeHigh;
							while (t.next != null){
								t = t.next;
							}
							t.next = new Entry<>(n.hash,n.key,n.value);
						}
					}
					n = n.next;
				}
				newTab[lowHash] = nodeLow;
				newTab[highHash] = nodeHigh;
			}
		}
		tab = newTab;
	}

	public V remove(K key) {
		V oldValue = null;
		int i;
		Entry<K, V> entry = tab[i = (hash(key) & (capacity - 1))];
		if (entry == null)
			return null;
		if (entry.hash == hash(key) && Objects.equals(key,entry.key)){
			oldValue = entry.value;
			if (entry.next == null){
				tab[i] = null;
			}else {
				tab[i] = entry.next;
				entry = null;
			}
			size--;
		}else {
			Entry<K,V> prev = entry;
			entry = entry.next;
			while (entry != null){
				if (entry.hash == hash(key) && Objects.equals(key,entry.key)){
					oldValue = entry.value;
					prev.next = entry.next;
					entry = null;
					size--;
					break;
				}
				prev = entry;
				entry = entry.next;
			}
		}
		return oldValue;
	}

	private int hash(K o){
		int h;
		return o == null ? 0 : (h = o.hashCode()) ^ h >>> 16;
	}

	public int size(){
		return size;
	}


	public static void main(String[] args) {
		HashMap<String,Integer> map = new HashMap<>(8);

		map.put("hllo",1);
		map.put("yes",2);
		map.put("wuadd",4);
		map.remove("hllo");
		System.out.println("aaa");
	}
}
