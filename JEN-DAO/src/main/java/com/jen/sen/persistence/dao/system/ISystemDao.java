package com.jen.sen.persistence.dao.system;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author jenson
 * @Date 2016-5-17
 */
import com.jen.sen.persistence.core.dao.FrameCenterDao;
import com.jen.sen.persistence.pojo.system.TUser;

public interface ISystemDao extends FrameCenterDao<TUser> {
	public List<Map<String, Object>> findMenuTreeByUser(Long userId);
	
	public List<Map<String, Object>> findMenuTreeAll();
	
	public TUser findByUserName(String userName) ;	
	
	public Set<String> findAllPerms();
	
	public Set<String> findPermsByUserId(Long Userid);
	
}
