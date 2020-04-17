package concurrent.Problem1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yogurtzzz
 * @date 2020/4/17 12:54
 *
 * 用Lock和Condition
 **/
public class Solution3LockCondition {

    static Thread t1;
    static Thread t2;
    static List<String> alphabet = new ArrayList<>(Arrays.asList("A","B","C","D","E","F"));
    static List<Integer> integers = new ArrayList<>(Arrays.asList(1,2,3,4,5,6));

    //这种写法有问题，必须要先await的线程先执行
//    public static void main(String[] args) {
//        Lock lock = new ReentrantLock();
//        //Condition是队列
//        Condition condition = lock.newCondition();
//
//        t1 = new Thread(()->{
//           try {
//               lock.lock();
//               for (String s : alphabet) {
//                   System.out.println(s);
//                   condition.signal();
//                   try {
//                       condition.await();
//                   } catch (InterruptedException e) {
//                       e.printStackTrace();
//                   }
//               }
//           }finally {
//               lock.unlock();
//           }
//        });
//
//        t2 = new Thread(()->{
//            try {
//                lock.lock();
//                for (int i : integers) {
//                    try {
//                        condition.await();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(i);
//                    condition.signal();
//                }
//            }finally {
//                lock.unlock();
//            }
//        });
//
//        /** 需要先启动t2，让t2先等待才行 **/
//        t2.start();
//        t1.start();
//    }

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();

        t1 = new Thread(()->{
           lock.lock();
           try {
               for (String s : alphabet) {
                   System.out.println(s);
                   c2.signal();
                   c1.await();
               }
               c2.signal();
           } catch (InterruptedException e) {
               e.printStackTrace();
           } finally {
               lock.unlock();
           }
        });
        t2 = new Thread(()->{
            lock.lock();
            try {
                for (int i : integers){
                    System.out.println(i);
                    c1.signal();
                    c2.await();
                }
                c1.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        t1.start();
        t2.start();
    }
}
