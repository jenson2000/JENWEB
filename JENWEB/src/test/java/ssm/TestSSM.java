package ssm;

import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.code.ssm.Cache;
import com.google.code.ssm.api.ParameterValueKeyProvider;
import com.google.code.ssm.api.ReadThroughSingleCache;
import com.google.code.ssm.api.format.SerializationType;
import com.google.code.ssm.providers.CacheException;
import com.jen.sen.persistence.dao.system.IUserDao;
import com.jen.sen.persistence.pojo.system.TUser;

import baset.BaseTest;

public class TestSSM extends BaseTest {
	


	@Autowired
	private IUserDao iUserDao;
	
	@Autowired
	private Cache appCache;
	
	@Test	
	@ReadThroughSingleCache(namespace="user")
	public void testUser(@ParameterValueKeyProvider String userId)  {
		System.out.println(userId);  
		TUser tu = iUserDao.findById(TUser.class, 1L);
		System.out.println("user"+tu.getUserId()+"--"+tu.getUserName()+"::userid:"+tu.getUserId());
		
	}
	
	// @Test	
	public void testApp()  {

		try {
			appCache.set("testapp",0, "testappppppppppwwwwwww",SerializationType.JAVA);
			System.out.println("testapp"+appCache.get("testapp", SerializationType.JAVA));
			
		} catch (TimeoutException | CacheException e) {
			System.out.println("errrrrr");
			e.printStackTrace();
		}
		
	}
	

}
