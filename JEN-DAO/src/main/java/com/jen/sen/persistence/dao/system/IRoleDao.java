package com.jen.sen.persistence.dao.system;

import java.util.List;
import java.util.Map;

import com.jen.sen.persistence.core.dao.FrameCenterDao;
import com.jen.sen.persistence.core.dao.tools.PageDT;
import com.jen.sen.persistence.pojo.system.TRoles;

/**
 * 
 * filename IRoleDao.java
 * company jen
 * @author jenson
 * @email jenson2000@sina.com
 */

public interface IRoleDao extends FrameCenterDao<TRoles> {
	
	public List<TRoles> queryPage(Map<String, Object> parms, PageDT page) ;
	
	public int queryRolesCount(Map<String, Object> parms) ;
	
	public void updateRolestate(String ids);
	
	public void saveOrupdateRoles(TRoles tu);
	
	public void saveOrupdateRoleAndRight(String rightids,TRoles tu);
	
	public List<TRoles> queryRoleByUserID(Long Userid) ;
	
	public void saveOrupdateRoleByUserId(Long userid,String ids) ;
	
	public  List<Map<String, Object>> queryRightTree();
	
	public  List<Map<String, Object>> queryRightTreeById(Long roleID);
	
}
