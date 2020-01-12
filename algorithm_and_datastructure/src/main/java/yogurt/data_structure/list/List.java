package yogurt.data_structure.list;

public interface List<T> extends Iterable<T>{
	T get(int index);
	boolean contains(T item);
	void add(T item);
	void add(int index,T item);
	void set(int index,T item);
	void remove(T item);
	void remove(int index);
	int size();
	boolean isEmpty();
}
