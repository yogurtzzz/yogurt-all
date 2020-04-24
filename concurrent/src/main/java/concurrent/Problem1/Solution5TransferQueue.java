package concurrent.Problem1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TransferQueue;

/**
 * @author yogurtzzz
 * @date 2020/4/24 12:42
 **/
public class Solution5TransferQueue {

    static Thread t1;
    static Thread t2;
    static List<String> alphabet = new ArrayList<>(Arrays.asList("A","B","C","D","E","F"));
    static List<String> integers = new ArrayList<>(Arrays.asList("1","2","3","4","5","6"));

    public static void main(String[] args) {
        TransferQueue<String> queue = new LinkedTransferQueue<>();
        t1 = new Thread(()->{
           for (String i : integers){
               try {
                   System.out.print(queue.take());
                   queue.transfer(i);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });

        t2 = new Thread(()->{
            for (String a : alphabet){
                try {
                    queue.transfer(a);
                    System.out.print(queue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}
