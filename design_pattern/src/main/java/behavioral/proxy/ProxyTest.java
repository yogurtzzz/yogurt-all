package behavioral.proxy;

import org.junit.Test;

public class ProxyTest {
	interface Service{
		void doService();
	}
	static class MassageService implements Service{
		public void doService(){
			System.out.println("doing service");
		}
	}
	static class ServiceWithoutInterface{
		public void doService(){
			System.out.println("doing service without interface");
		}
	}

	@Test
	public void testJDK(){
		/**
		 * JDK动态代理，被代理对象必须实现接口
		 */
		Service target = new MassageService();
		Service proxy = (Service) JDKProxy.getProxy(target);
		proxy.doService();
	}

	@Test
	public void testCGLib(){
		/**
		 * CGlib动态代理，生成的代理对象是目标对象的子类
		 */
		ServiceWithoutInterface proxy = (ServiceWithoutInterface) CGLibProxy.getProxy(ServiceWithoutInterface.class);
		proxy.doService();
	}
}
