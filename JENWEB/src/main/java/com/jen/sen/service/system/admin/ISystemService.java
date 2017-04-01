package com.jen.sen.service.system.admin;

import java.util.List;
import java.util.Set;

import com.jen.sen.exception.AjaxException;
import com.jen.sen.web.component.MenuTreeComponent;
import com.jen.sen.web.vo.system.UserVo;

public interface ISystemService {

	public List<MenuTreeComponent> findMenuTreeByUser(Long userId) throws AjaxException;	
	
	public UserVo findByUserName(String userName) throws AjaxException;
	
	public Set<String> findAllPerms() throws AjaxException;
	
	public Set<String> findPermsByUserId(Long Userid) throws AjaxException;
	//更新所有授权缓存
	public Set<String> findClsAllPerms() throws AjaxException;
	//按用户更新授权缓存
	public Set<String> findClsPermsByUserId(Long Userid) throws AjaxException;
	//更新用户菜单
	public List<MenuTreeComponent> findClsMenuTreeByUser(Long userId) throws AjaxException;
	//删除所有授权缓存
	public String findClsAllCache() throws AjaxException;
}
