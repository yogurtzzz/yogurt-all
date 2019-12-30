package JVM;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MethodAreaLeak {
	static class OOM{}
	public static void main(String[] args) {
		while(true){
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(OOM.class);
			enhancer.setUseCache(false);
			enhancer.setCallback((MethodInterceptor) (obj, method, args1, proxy) -> proxy.invokeSuper(obj, args1));
			enhancer.create();
		}
	}
}
