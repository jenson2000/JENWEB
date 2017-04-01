package com.jen.sen.service.system.user;

import java.util.List;
import java.util.Map;

import com.jen.sen.exception.AjaxException;
import com.jen.sen.persistence.core.dao.tools.PageDT;
import com.jen.sen.web.vo.system.UserVo;

public interface IUserService {
	
	public UserVo findUser(Long userid) throws AjaxException;
	
	public List<UserVo> findUser() throws AjaxException;
	
	public List<UserVo> findUserPage(Map<String, Object> parms, PageDT page) throws AjaxException;
	
	public int findUserPageCount(Map<String, Object> parms) throws AjaxException;
	public void updateUserState(String ids) throws AjaxException;
	
	public void saveOrupdateUser(UserVo vo) throws Exception;
	

}
