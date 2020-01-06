package jdk8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StreamUtil {

    @Test
    public void concatString(){
        List<String> list = Arrays.asList("China","America","Japan","Finland");
        String concat = list.stream().collect(Collectors.joining("/"));
        System.out.println(concat);
    }

    @Test
    public void test(){
        List<String> list = Collections.EMPTY_LIST;
        String collect = list.stream().collect(Collectors.joining("/"));

        if (list.isEmpty())
            System.out.println("empty");
        else
            System.out.println("not");
        System.out.println(collect);
    }

    /**
     * 使用flatMap来扁平化一个stream，可以达到合并多个List的效果，如下
     * **/
    @Test
    public void testFlatMap(){
        class Yogurt{
            private List<String> innerList;
            public List<String> getInnerList() {
                return innerList;
            }
            public void setInnerList(List<String> innerList) {
                this.innerList = innerList;
            }
        }
        List<Yogurt> yogurtList = new ArrayList<>();
        Yogurt y1 = new Yogurt();
        Yogurt y2 = new Yogurt();

        List<String> in1 = Arrays.asList("hello","world","this","is","yogurt");
        List<String> in2 = Arrays.asList("can","you","merge","this","?");
        y1.setInnerList(in1);
        y2.setInnerList(in2);

        yogurtList.add(y1);
        yogurtList.add(y2);

        List<String> merge = yogurtList.stream().flatMap(v->v.getInnerList().stream()).collect(Collectors.toList());
        merge.forEach(v-> System.out.println(v));
    }
}
