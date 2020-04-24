package concurrent.Problem1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author yogurtzzz
 * @date 2020/4/24 12:38
 **/
public class Solution4BlockingQueue {
    static Thread t1;
    static Thread t2;
    static List<String> alphabet = new ArrayList<>(Arrays.asList("A","B","C","D","E","F"));
    static List<Integer> integers = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));

    static BlockingQueue<String> queue1 = new ArrayBlockingQueue<>(1);
    static BlockingQueue<String> queue2 = new ArrayBlockingQueue<>(1);

    public static void main(String[] args) throws InterruptedException {
        t1 = new Thread(()->{
           for (String s : alphabet){
               try {
                   queue1.take();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.print(s);
               try {
                   queue2.put("ok");
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });

        t2 = new Thread(()->{
           for (int i : integers){
               try {
                   queue2.take();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.print(i);
               try {
                   queue1.put("ok");
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });

        queue1.put("ok");
        t1.start();
        t2.start();
    }
}
