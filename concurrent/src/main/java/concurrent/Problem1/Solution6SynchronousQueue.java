package concurrent.Problem1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

/**
 * @author yogurtzzz
 * @date 2020/4/26 11:42
 **/
public class Solution6SynchronousQueue {

    static Thread t1;
    static Thread t2;
    static List<String> alphabet = new ArrayList<>(Arrays.asList("A","B","C","D","E","F"));
    static List<String> integers = new ArrayList<>(Arrays.asList("1","2","3","4","5","6"));

    public static void main(String[] args) {
        SynchronousQueue<String> queue = new SynchronousQueue<>();

        t1 = new Thread(()->{
           for (String s : alphabet){
               System.out.print(s);
               try {
                   queue.put("ok");
                   queue.take();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });

        t2 = new Thread(()->{
           for (String i : integers){
               try {
                   String s = queue.take();
                   System.out.print(i);
                   queue.put("ok");
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });

        t1.start();
        t2.start();
    }
}
