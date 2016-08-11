package next.aop;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class HelloTargetTest {

	@Test
	public void tsetHello() throws Exception {
		Hello hello = new HelloTarget();
		HelloUppercase proxy = new HelloUppercase(hello);
		System.out.println(proxy.sayHello("t1t1"));
	}
	
}
