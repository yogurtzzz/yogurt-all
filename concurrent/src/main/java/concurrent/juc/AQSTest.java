package concurrent.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yogurtzzz
 * @date 2020/4/21 12:19
 **/
public class AQSTest {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        new Thread(()->{
            lock.lock();
        }).start();

        new Thread(()->{
            lock.lock();
        }).start();
    }
}
