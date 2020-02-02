package yogurt.data_structure.list;

import java.util.*;

/**
 * 自实现的ArrayList
 * @param <T>
 */
public class ArrayList<T> implements List<T>{

	private transient Object[] elementData;
	private int modCount;
	private int size;
	private static final int DEFAULT_CAPACITY = 10;
	private static final Object[] DEFAULT_EMPTY_ELEMENT_DATA = {};

	public ArrayList(){
		elementData = DEFAULT_EMPTY_ELEMENT_DATA;
	}
	public ArrayList(int initialCapacity){
		if (initialCapacity < 0)
			throw new IllegalArgumentException("Initial Capacity cannot be negative");
		elementData = new Object[initialCapacity];
	}

	@Override
	public T get(int index) {
		checkIndex(index);
		return (T)elementData[index];
	}

	@Override
	public boolean contains(T item) {
		for (int i = 0; i < size; i++){
			if (Objects.equals(elementData[i],item))
				return true;
		}
		return false;
	}

	@Override
	public void add(T item) {
		ensureInternalCapacity(size + 1);
		elementData[size++] = item;
		modCount++;
	}

	private void ensureInternalCapacity(int minCapacity) {
		int oldCap = elementData.length;
		int newCap;
		if(minCapacity > oldCap){
			if (elementData == DEFAULT_EMPTY_ELEMENT_DATA){
				newCap = DEFAULT_CAPACITY;
			}else{
				//try to expand to 1.5
				oldCap = oldCap + (oldCap >> 1);
				if (minCapacity > oldCap){
					newCap = minCapacity;
				}else{
					newCap = oldCap;
				}
			}
			Object[] newElementData = new Object[newCap];
			System.arraycopy(elementData,0,newElementData,0,elementData.length);
			elementData = newElementData;
		}
	}

	@Override
	public void add(int index, T item) {
		checkIndex(index);
		ensureInternalCapacity(size + 1);
		System.arraycopy(elementData,index,elementData,index +1 ,size - index);
		elementData[index] = item;
		size++;
		modCount++;
	}

	@Override
	public void set(int index, T item) {
		checkIndex(index);
		elementData[index] = item;
	}

	@Override
	public void remove(T item) {
		for (int i = 0 ; i < size; i++){
			if (Objects.equals(elementData[i],item)){
				if (i != size - 1){
					System.arraycopy(elementData,i + 1,elementData,i,size - i - 1);
				}
				elementData[--size] = null;
				modCount++;
				return ;
			}
		}
	}

	@Override
	public void remove(int index) {
		checkIndex(index);
		if (index != size - 1){
			System.arraycopy(elementData,index + 1,elementData,index,size - index - 1);
		}
		elementData[--size] = null;
		modCount++;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	private void checkIndex(int index){
		if (index < 0 || index >= size)
			throw new IllegalArgumentException("Index Out Of Bound Exception");
	}

	public T[] toArray(T[] a){
		return (T[])Arrays.copyOf(elementData, size, a.getClass());
	}

	public Iterator<T> iterator(){
		return new Iterator<T>() {
			private int cursor = 0;
			private int lastRet = -1;
			private int expectedModCount = modCount;
			@Override
			public boolean hasNext() {
				return cursor != size;
			}

			@Override
			public T next() {
				//fail fast
				checkForModification();
				if (cursor >= size)
					throw new NoSuchElementException();
				Object ret = elementData[cursor];
				lastRet = cursor;
				cursor++;
				return (T)ret;
			}

			@Override
			public void remove() {
				if (lastRet == -1)
					throw new IllegalStateException();
				ArrayList.this.remove(lastRet);
				cursor = lastRet;
				lastRet = -1;
				expectedModCount = modCount;
			}

			private void checkForModification(){
				if (this.expectedModCount != modCount)
					throw new ConcurrentModificationException();
			}
		};
	}
}

