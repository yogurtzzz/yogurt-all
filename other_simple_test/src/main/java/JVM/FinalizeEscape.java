package JVM;

import sun.misc.Unsafe;
import sun.nio.ch.ServerSocketAdaptor;

import java.lang.reflect.Field;

public class FinalizeEscape {
	private static Object SAVE_HOOK;

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		System.out.println("finalizing");
		SAVE_HOOK = this;
	}

	public static void main(String[] args) throws InterruptedException {
		SAVE_HOOK = new FinalizeEscape();
		SAVE_HOOK = null;
		System.gc();
		Thread.sleep(500);
		if (SAVE_HOOK != null){
			System.out.println("成功拯救");
		}else {
			System.out.println("死掉了");
		}

		SAVE_HOOK = null;
		System.gc();
		Thread.sleep(500);
		if (SAVE_HOOK != null){
			System.out.println("成功拯救");
		}else {
			System.out.println("死掉了");
		}
	}
}
