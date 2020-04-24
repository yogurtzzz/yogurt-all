package concurrent.Problem1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yogurtzzz
 * @date 2020/4/24 12:34
 **/
public class Solution3CAS {
    enum READY_TO_RUN{
        T1,
        T2
    }

    static volatile READY_TO_RUN readyToRun = READY_TO_RUN.T1;

    static Thread t1;
    static Thread t2;
    static List<String> alphabet = new ArrayList<>(Arrays.asList("A","B","C","D","E","F"));
    static List<Integer> integers = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));

    public static void main(String[] args) {

        t1 = new Thread(()->{
            for (String s : alphabet){
                while (readyToRun != READY_TO_RUN.T1){
                }
                System.out.print(s);
                readyToRun = READY_TO_RUN.T2;
            }
        });

        t2 = new Thread(()->{
           for (int i : integers){
               while (readyToRun != READY_TO_RUN.T2){
               }
               System.out.print(i);
               readyToRun = READY_TO_RUN.T1;
           }
        });

        t1.start();
        t2.start();
    }
}
