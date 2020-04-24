package concurrent.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author yogurtzzz
 * @date 2020/4/21 14:28
 **/
public class CASABA {
    public static void main(String[] args) {
        ABASolution();
    }

    private static void ABA(){
        AtomicInteger atomicInteger = new AtomicInteger(0);

        Thread t1 = new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = atomicInteger.compareAndSet(0, 3);
            if (b){
                System.out.println("CAS success " + atomicInteger.get());
            }
        },"mainThread");

        Thread t2 = new Thread(()->{
            atomicInteger.compareAndSet(0,1);
            System.out.println("other thread " + atomicInteger.get());
            atomicInteger.compareAndSet(1,0);
            System.out.println("other thread " + atomicInteger.get());
        },"otherThread");

        t1.start();
        t2.start();
    }

    private static void ABASolution(){
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<>(1,0);
        Thread t1 = new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean b = atomicStampedReference.compareAndSet(1, 5, stamp, stamp + 1);
            System.out.println("main thread CAS success ? " + b + " value = " + atomicStampedReference.getReference());
        },"main thread");

        Thread t2 = new Thread(()->{
            int stamp = atomicStampedReference.getStamp();
            boolean b = atomicStampedReference.compareAndSet(1, 2, stamp, stamp + 1);
            System.out.println("other thread CAS success ? " + b + " value = " + atomicStampedReference.getReference());
            int stamp1 = atomicStampedReference.getStamp();
            boolean b1 = atomicStampedReference.compareAndSet(2, 1, stamp1, stamp1 + 1);
            System.out.println("other thread CAS success ? " + b1 + " value = " + atomicStampedReference.getReference());
        });

        t1.start();
        t2.start();
    }
}
