package yogurt.data_structure.queue;


import yogurt.data_structure.list.ArrayList;
import yogurt.data_structure.list.List;

public class PriorityQueue<T extends Comparable> {
	private List<T> heap;

	public PriorityQueue(){
		heap = new ArrayList<>();
	}
	public void offer(T ele){
		heap.add(ele);
		upAdjust(heap.size() - 1);
	}
	public T poll(){
		if (heap.isEmpty())
			return null;
		T t = heap.get(0);
		heap.set(0,heap.get(heap.size() - 1));
		heap.remove(heap.size() - 1);
		downAdjust(0);
		return t;
	}

	private void upAdjust(int pos){
		int cur = pos;
		T value = heap.get(pos);
		while (cur > 0){
			int parent = (cur - 1) / 2;
			if (value.compareTo(heap.get(parent)) < 0){
				heap.set(cur,heap.get(parent));
				cur = parent;
			}else
				break;
		}
		heap.set(cur,value);
	}
	private void downAdjust(int pos){
		int cur = pos;
		while (cur < heap.size()){
			int right = cur * 2 + 2;
			if (right < heap.size()){
				right = heap.get(right - 1).compareTo(heap.get(right)) < 0 ? right - 1 : right;
			}else if (right - 1 < heap.size())
				right--;
			else
				break;
			if (heap.get(right).compareTo(heap.get(cur)) < 0){
				T temp = heap.get(right);
				heap.set(right,heap.get(cur));
				heap.set(cur,temp);
				cur = right;
			}else
				break;
		}
	}

	public static void main(String[] args) {
		PriorityQueue<Integer> ha = new PriorityQueue<>();
		ha.offer(2);
		ha.offer(4);
		ha.offer(6);
		ha.offer(1);
		ha.offer(3);
		ha.offer(9);
		ha.offer(23);
		ha.offer(11);

		Integer a ;
		while ((a = ha.poll()) != null){
			System.out.println(a);
		}
	}
}
