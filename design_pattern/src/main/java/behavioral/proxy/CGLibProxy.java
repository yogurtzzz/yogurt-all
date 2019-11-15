package behavioral.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLib是基于继承的，通过生成目标类的子类，来完成代理
 */
public class CGLibProxy implements MethodInterceptor {
	/**
	 *
	 * @param proxy  代理对象，即 目标对象的 子类
	 * @param method 目标对象的方法
	 * @param args  参数
	 * @param methodProxy  代理对象的方法
	 * @return
	 * @throws Throwable
	 */
	@Override
	public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
		System.out.println("CGLib Pre Process");
		Object retVal = methodProxy.invokeSuper(proxy,args);
		System.out.println("CGLib Post Process");
		return retVal;
	}
	public static Object getProxy(Class clzz){
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(clzz);
		enhancer.setCallback(new CGLibProxy());
		return enhancer.create();
	}
}
