package graph;

import java.util.List;

public class ListGraph<V> implements Graph<V> {
    class Vertex{
        private V v;
        private List<Edge<V>> edges;

    }

    @Override
    public void add(V v) {

    }

    @Override
    public void remove(V v) {

    }

    @Override
    public void add(Edge<V> e) {

    }

    @Override
    public void remove(Edge<V> e) {

    }

    @Override
    public V get(int index) {
        return null;
    }

    @Override
    public Edge<V> get(int start, int end) {
        return null;
    }
}
