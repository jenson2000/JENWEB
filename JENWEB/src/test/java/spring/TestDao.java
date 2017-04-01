package spring;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.jen.sen.persistence.dao.system.IUserDao;
import com.jen.sen.persistence.pojo.system.TUser;

import baset.BaseTest;



public class TestDao extends BaseTest {
	
	@Autowired
	private IUserDao iUserDao;
	
	@Test
	public void testUser()  {
		TUser tu = iUserDao.findById(TUser.class, 1L);
		TUser tu1 = iUserDao.findById(TUser.class, 1L);
		TUser tu2 = iUserDao.findById(TUser.class, 1L);
		System.out.println("user"+tu.getUserAccount()+"--"+tu.getUserName());
		System.out.println("user"+tu1.getUserAccount()+"--"+tu1.getUserName());
		System.out.println("user"+tu2.getUserAccount()+"--"+tu2.getUserName());
	}
		
	


}
