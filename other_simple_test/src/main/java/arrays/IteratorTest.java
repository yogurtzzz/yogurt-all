package arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class IteratorTest {
    //
    public void test(){
        List<Integer> list = new ArrayList<>();
        list.add(5);
        list.add(6);
        list.add(7);

        Iterator<Integer> it = list.iterator();
        while (it.hasNext()){
            Integer i = it.next();
            System.out.println(i);
        }
    }


}
