package creational;


/**
 * 双重检查锁单例
 */
public class DoubleCheckSingleton {
	private static DoubleCheckSingleton singleton = null;

	/**
	 * 限制住
	 */
	private DoubleCheckSingleton(){}

	public static DoubleCheckSingleton getInstance(){
		if (singleton == null){
			synchronized (DoubleCheckSingleton.class){
				if (singleton == null){
					singleton = new DoubleCheckSingleton();  //this step is not atomic, may lead to fatal error
				}
			}
		}
		return singleton;
	}
}
