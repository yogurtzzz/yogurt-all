package multiThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolTest {

	static class Fuck implements Callable<Integer>{

		@Override
		public Integer call() throws Exception {
			System.out.println("#" + Thread.currentThread().getName() + " is Calculating...");
			int sum = 0;
			Thread.sleep(1000 * 600);
			System.out.println("$" + Thread.currentThread().getName() + " ended");
			return sum;
		}
	}

	public static void main(String[] args) throws ExecutionException, InterruptedException {

		int coreSize = 5;
		int maxSize = 10;
		long keepAliveTime = 10;
		TimeUnit timeUnit = TimeUnit.SECONDS;
		RejectedExecutionHandler rejectPolicy = new ThreadPoolExecutor.AbortPolicy();
		BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(20);
		ThreadPoolExecutor pool = new ThreadPoolExecutor(coreSize,maxSize,keepAliveTime,timeUnit,queue,rejectPolicy);

		List<Future<Integer>> futures = new ArrayList<>();
		for (int i = 0; i < 100; i++){
			Future<Integer> intege = pool.submit(new Fuck());
			futures.add(intege);
		}

		for (Future<Integer> item : futures){
			System.out.println(item.get());
		}
	}
}
