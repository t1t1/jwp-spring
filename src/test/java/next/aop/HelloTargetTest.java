package next.aop;

import java.lang.reflect.Proxy;

import org.junit.Test;
import org.springframework.aop.framework.ProxyFactoryBean;

public class HelloTargetTest {

	@Test
	public void tsetHello() throws Exception {
		Hello hello = new HelloTarget();
		HelloUppercase proxy = new HelloUppercase(hello);
		System.out.println(proxy.sayHello("t1t1"));
	}
	
	@Test
	public void simpleProxy() throws Exception {
		Hello proxiedHello = (Hello) Proxy.newProxyInstance(getClass()
				.getClassLoader(), new Class[] { Hello.class },
				new UppercaseHandler(new HelloTarget()));
		System.out.println(proxiedHello.sayHello("t1t1"));
		System.out.println(proxiedHello.sayHi("t1t1"));
	}
	
	@Test
	public void testSpringProxyFactoryBean() throws Exception {
		ProxyFactoryBean pfBean = new ProxyFactoryBean();
		pfBean.setTarget(new HelloTarget());
		pfBean.addAdvice(new UppercaseAdvice());
		Hello proxiedHello = (Hello) pfBean.getObject();
		System.out.println(proxiedHello.sayHello("test Spring ProxyFactoryBean"));
	}
	
}
