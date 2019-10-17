package arrays;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CompareTest {
    @Test
    public void test(){
        Person p1 = new Person("Bob",23,60);
        Person p2 = new Person("Alice",21,80);
        Person p3 = new Person("Yogurt",18,30);
        Person p4 = new Person("Phillip",25,100);
        Person p5 = new Person("Catchy",10,10);
        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        list.add(p5);

        Collections.sort(list, Comparator.comparingInt(Person::getScore));
        for (Person item : list){
            System.out.println(item);
        }
    }
}
