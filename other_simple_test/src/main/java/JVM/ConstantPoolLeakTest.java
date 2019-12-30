package JVM;

import java.util.ArrayList;
import java.util.List;

public class ConstantPoolLeakTest {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		int i = 0;
		while (true){
			list.add(String.valueOf(i++).intern());
		}
	}
}
