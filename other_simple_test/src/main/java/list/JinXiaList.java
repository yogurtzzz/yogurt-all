package list;

public class JinXiaList {
	JinXiaNode head;
	JinXiaNode tail;
	int size;

	public JinXiaNode init(int num){
		JinXiaNode node = new JinXiaNode();
		node.value = num;
		node.next = null;
		head = node;
		tail = node;
		size = 1;
		return head;
	}

	public void add(int num){
		JinXiaNode node = new JinXiaNode();
		node.value = num;
		node.next = null;
		tail.next = node;
		tail = node;
		size++;
	}
	public void tranverse(){
		JinXiaNode cursor = head;
		while (cursor != null){
			System.out.print(cursor.value + " ");
			cursor = cursor.next;
		}
	}

	public int getI(int i){
		checkIndex(i);
		JinXiaNode cursor = head;
		while (i > 0){
			cursor = cursor.next;
			i--;
		}
		return cursor.value;
	}

	public int remove(int i){
		checkIndex(i);
		JinXiaNode cursor = head;
		JinXiaNode prev = null;
		while (i > 0){
			prev = cursor;
			cursor = cursor.next;
			i--;
		}
		if (prev == null){
			head = cursor.next;
			cursor.next = null;
		}else{
			prev.next = cursor.next;
			cursor.next = null;
		}
		size--;
		return cursor.value;
	}

	public void insert(int i,int num){
		checkIndex(i);
		JinXiaNode node = new JinXiaNode();
		node.value = num;

		JinXiaNode prev = null;
		JinXiaNode cursor = head;

		while (i > 0){
			prev = cursor;
			cursor = cursor.next;
			i--;
		}
		if (prev == null){
			node.next = head;
			head = node;
		}else{
			prev.next = node;
			node.next = cursor;
		}
	}

	public void checkIndex(int i){
		if (i < 0 || i > size - 1){
			throw new IllegalArgumentException("数组越界");
		}

	}

	public static void main(String[] args) {
		JinXiaList list = new JinXiaList();
		JinXiaNode head = list.init(1);
		list.add(2);
		list.add(5);
		list.add(-5);
		list.add(7);
	}
}
