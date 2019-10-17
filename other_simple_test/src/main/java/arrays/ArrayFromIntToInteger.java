package arrays;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ArrayFromIntToInteger {
    @Test
    public void intToInteger(){
        int[] arr = {1,2,3,4,5};

        // int[] 转 List
        List<Integer> IntegerList = Arrays.stream(arr).boxed().collect(Collectors.toList());

        //int[] 转 Integer[]
        Integer[] Integers = Arrays.stream(arr).boxed().toArray(Integer[]::new);

        //List 转 Integer[]
        Integer[] Integers2 = IntegerList.toArray(new Integer[0]);

        //List 转 int[]
        int[] arr2 = IntegerList.stream().mapToInt(Integer::valueOf).toArray();

        //Integer[] 转 int[]
        int[] arr3 = Arrays.stream(Integers).mapToInt(Integer::valueOf).toArray();
    }
}
