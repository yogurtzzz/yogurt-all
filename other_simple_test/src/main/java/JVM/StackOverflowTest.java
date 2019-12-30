package JVM;

public class StackOverflowTest {
	private int stackLength = 1;
	public void stackLeak(){
		stackLength++;
		stackLeak();
	}
	public static void main(String[] args) {
		StackOverflowTest stackOverflowTest = new StackOverflowTest();
		try{
			stackOverflowTest.stackLeak();
		}catch (Throwable e){
			System.out.println(stackOverflowTest.stackLength);
			System.out.println(e.toString());
		}
	}

}
