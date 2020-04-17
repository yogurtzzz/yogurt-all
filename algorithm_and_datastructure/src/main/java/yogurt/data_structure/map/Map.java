package yogurt.data_structure.map;

import java.util.Set;

/**
 * @author yogurtzzz
 * @date 2020/4/13 17:37
 **/
public interface Map<K,V> {

    int size();

    boolean isEmpty();

    boolean containsKey(Object key);

    boolean containsValue(Object value);

    V get(K key);

    V put(K key,V value);

    V remove(K k);

    Set<K> keySet();

    interface Entry<K,V>{

        K getKey();

        V getValue();

        V setValue(V value);

        int hashCode();
    }

}
