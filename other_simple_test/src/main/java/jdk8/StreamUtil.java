package jdk8;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamUtil {

    @Test
    public void concatString(){
        List<String> list = Arrays.asList("China","America","Japan","Finland");
        String concat = list.stream().collect(Collectors.joining("/"));
        System.out.println(concat);
    }
}
