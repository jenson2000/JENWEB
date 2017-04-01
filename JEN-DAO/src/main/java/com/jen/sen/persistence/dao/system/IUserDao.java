package com.jen.sen.persistence.dao.system;

import java.util.List;
import java.util.Map;

import com.jen.sen.persistence.core.dao.FrameCenterDao;
import com.jen.sen.persistence.core.dao.tools.PageDT;
import com.jen.sen.persistence.pojo.system.TUser;

/**
 * 
 * filename IUserDao.java
 * company jen
 * @author jenson
 * @email jenson2000@sina.com
 */

public interface IUserDao extends FrameCenterDao<TUser> {
	
	public List<TUser> queryPage(Map<String, Object> parms, PageDT page) ;
	
	public int queryUserCount(Map<String, Object> parms) ;
	
	public void updateUserstate(String ids);
	
	public void saveOrupdateUser(TUser tu);
	

}
