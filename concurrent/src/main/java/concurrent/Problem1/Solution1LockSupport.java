package concurrent.Problem1;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.LockSupport;

/**
 * @author yogurtzzz
 * @date 2020/4/17 9:20
 *
 * 2个线程交替打印
 * 一个线程打印ABCD字母
 * 一个线程打印1234数字
 * 要求先打印字母
 * A1B2C3D4
 *
 * 用LockSupport的park和unpark
 **/
public class Solution1LockSupport {

    static Thread t1;
    static Thread t2;
    static List<String> alphabet = new ArrayList<>(Arrays.asList("A","B","C","D","E","F"));
    static List<Integer> integers = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));

    public static void main(String[] args) throws InterruptedException {

        t1 = new Thread(()->{
            for (String s : alphabet){
                System.out.println(s);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        });

        t2 = new Thread(()->{
            for (int i : integers){
                LockSupport.park();
                System.out.println(i);
                LockSupport.unpark(t1);
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
