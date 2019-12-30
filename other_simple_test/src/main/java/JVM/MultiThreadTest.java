package JVM;

import java.util.ArrayList;
import java.util.List;

public class MultiThreadTest {
	public static void main(String[] args) {
		int count = 0;
		while (true){
			Thread t = new Thread(() -> {
				//System.out.println("I'm thread " + Thread.currentThread().getName());
				while(true){
					//让这个线程不死
				}
			});
			t.start();
			count++;
			System.out.println(count);
		}
	}
}
