package list;

import java.util.Iterator;

public interface YogurtList<T> extends Iterable<T>{
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
