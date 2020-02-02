package utils;

public class Assert {
	public static void assertTrue(boolean expected){
		if (!expected)
			throw new RuntimeException();
	}
}
