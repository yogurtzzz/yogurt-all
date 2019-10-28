package multiThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {
    static class YogurtThread implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            int sum = 0;
            for (int i = 1 ; i <= 100; i++){
                System.out.println("Calculating...");
                Thread.sleep(10);
                sum += i;
            }
            return sum;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new YogurtThread());
        Thread thread = new Thread(futureTask);
        thread.start();
        int result = futureTask.get();
        System.out.println(result);
    }
}
