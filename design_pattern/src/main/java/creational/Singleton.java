package creational;


public class Singleton {
	/**
	 * 双重检查锁单例
	 */
	private static Singleton singleton = null;
	private Singleton(){}

	public static Singleton getInstance(){
		if (singleton == null){
			synchronized (Singleton.class){
				if (singleton == null){
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}
}
