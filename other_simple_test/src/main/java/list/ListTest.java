package list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListTest {
    @Test
    public void test1(){
        int[] arrInt = {1,2,3,4,5,6};
        arrInt = enlarge(arrInt,10);
        System.arraycopy(arrInt,3,arrInt,2,6 - 2 - 1);
        for (int item : arrInt){
            System.out.println(item);
        }
    }

    @Test
    public void test2(){
        YogurtList<String> list = new YogurtArrayList<>();
        for (int i = 0 ; i < 15; i++){
            list.add("Hello" + i);
        }
        list.add(0,"fuck");
        for (String item : list){
            System.out.println(item);
        }
    }

    public int[] enlarge(int[] src,int size){
        if (size <= src.length)
            return src;
        int[] dest = new int[size];
        System.arraycopy(src,0,dest,0,src.length);
        return dest;
    }
}
