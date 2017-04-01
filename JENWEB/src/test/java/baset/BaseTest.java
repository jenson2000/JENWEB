package baset;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//,"/applicationContext-memcache.xml"
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationContext.xml","/applicationContext-dbSource.xml","/dispatcherServletContext.xml" })
public class BaseTest {
	
	@BeforeClass
	public static void testbf() {
		System.out.println("test begin========");
	}
	
	@AfterClass
	public static void testaf() {
		System.out.println("test end========");
	}

}
