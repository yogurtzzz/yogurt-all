package test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TestAAA {
    static int i;
    @Test
    public void test(){
        float[][] f1 = {{1.2f,2.3f},{4.5f,5.6f}};
        Object oo = f1;
    }
    @Test
    public void tes2(){
        ArrayList a = new ArrayList();
        AC b = new AC();
    }

    public static void main(String[] args) {
        int i = 2;
        while(i-- > 0){
            int initialCapacity = new Scanner(System.in).nextInt();
            initialCapacity |= (initialCapacity >>>  1);
            initialCapacity |= (initialCapacity >>>  2);
            initialCapacity |= (initialCapacity >>>  4);
            initialCapacity |= (initialCapacity >>>  8);
            initialCapacity |= (initialCapacity >>> 16);
            initialCapacity++;
            System.out.println(initialCapacity);
        }
    }
}
