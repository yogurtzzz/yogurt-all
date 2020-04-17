package concurrent.Problem1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yogurtzzz
 * @date 2020/4/17 12:53
 *
 * 用Object的wait和notify
 **/
public class Solution2ObjectWaitNotify {

    static Thread t1;
    static Thread t2;
    static List<String> alphabet = new ArrayList<>(Arrays.asList("A","B","C","D","E","F"));
    static List<Integer> integers = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));

    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();

        new Thread(()->{
            synchronized (o){
                for (int i : integers){
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i);
                    o.notify();
                }
            }
        }).start();

        new Thread(()->{
            synchronized (o){
                for (String s : alphabet){
                    System.out.println(s);
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
