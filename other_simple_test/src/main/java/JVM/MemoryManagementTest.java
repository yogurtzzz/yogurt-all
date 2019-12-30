package JVM;

import java.util.ArrayList;
import java.util.List;

public class MemoryManagementTest {
	private static final int _1MB = 1024 * 1024;

	public static void main(String[] args) {
		List<byte[]> list = new ArrayList<>();
		while (true){
			byte[] bs = new byte[(int)(_1MB )];
			list.add(bs);
		}
	}
}
