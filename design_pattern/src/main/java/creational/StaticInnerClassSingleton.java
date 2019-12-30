package creational;

/**
 * 静态内部类单例
 */
public class StaticInnerClassSingleton {

	private StaticInnerClassSingleton(){}

	/**
	 * 加载外部类时，不会立即加载内部类
	 */
	static class SingletonHolder{
		private static StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
	}

	public static StaticInnerClassSingleton getInstance(){
		return SingletonHolder.instance;
	}
}
