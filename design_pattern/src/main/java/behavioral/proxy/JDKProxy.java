package behavioral.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements InvocationHandler {
	private Object target;
	public JDKProxy(Object target){
		this.target = target;
	}

	/**
	 *
	 * @param proxy  代理对象
	 *               proxy参数存在的意义：
	 *                  可以通过反射来获取这个代理对象的相关信息
	 *                  若不关心方法的返回值，可以返回proxy对象，以实现链式调用，如这个方法的语义是存钱
	 * @param method 目标对象方法
	 * @param args 参数
	 * @return
	 * @throws Throwable
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object retVal;
		System.out.println("JDK Proxy Pre Process");
		retVal = method.invoke(target,args);
		System.out.println("JDK Proxy Post Process");
		return retVal;
	}
	public static Object getProxy(Object target){
		return Proxy.newProxyInstance(target.getClass().getClassLoader(),
				target.getClass().getInterfaces(),
				new JDKProxy(target));
	}
}
