package list;

import java.util.Iterator;
import java.util.ListIterator;

public class YogurtLinkedList<T> implements YogurtList<T> {
    @Override
    public Iterator<T> iterator() {
        return null;
    }

    class Node<T>{
        T data;
        Node<T> next;
        Node<T> prev;
        public Node(T data,Node next,Node prev){
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;
    private int modCount;

    private void checkIndex(int index){
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
    }
    private Node<T> node(int index){
        checkIndex(index);
        if (index == 0)
            return head;
        if (index == size - 1)
            return tail;
        Node<T> res;
        if (index < size / 2){
            res = head;
            for (int i = 1; i <= index; i++){
                res = res.next;
            }
            return res;
        }else{
            res = tail;
            for (int i = 1; i <= size - index - 1; i--){
                res = res.prev;
            }
            return res;
        }
    }
    @Override
    public T get(int index) {
        return (T)node(index);
    }

    @Override
    public boolean contains(T item) {
        return false;
    }

    @Override
    public void add(T item) {
        linkLast(item);
    }

    private void linkLast(T item) {
        Node l = tail;
        Node node = new Node(item,null,l);
        tail = node;
        if (head == null){
            head = node;
        }else{
            l.next = node;
        }
        size++;
        modCount++;
    }

    @Override
    public void add(int index, T item) {

    }

    @Override
    public void set(int index, T item) {

    }

    @Override
    public void remove(T item) {

    }

    @Override
    public void remove(int index) {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

}
