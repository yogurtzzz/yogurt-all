package graph;

import java.util.List;

public interface Graph<V> {
    void add(V v);
    void remove(V v);
    void add(Edge<V> e);
    void remove(Edge<V> e);
    V get(int index);
    Edge<V> get(int start,int end);
}
