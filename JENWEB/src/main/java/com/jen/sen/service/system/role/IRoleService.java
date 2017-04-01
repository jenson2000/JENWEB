package com.jen.sen.service.system.role;

import java.util.List;
import java.util.Map;

import com.jen.sen.exception.AjaxException;
import com.jen.sen.persistence.core.dao.tools.PageDT;
import com.jen.sen.persistence.pojo.system.TRoles;
import com.jen.sen.web.vo.system.RoleVo;

public interface IRoleService {
	
	public RoleVo findRole(Long Roleid) throws AjaxException;
	
	public List<RoleVo> findRoleByUserId(Long UserId) throws AjaxException;
	
	public List<RoleVo> findRole() throws AjaxException;
	
	public List<RoleVo> findRolePage(Map<String, Object> parms, PageDT page) throws AjaxException;
	
	public int findRolePageCount(Map<String, Object> parms) throws AjaxException;
	
	public void updateRoleState(String ids) throws AjaxException;
	
	public void saveOrupdateRole(RoleVo vo) throws Exception;
	
	public void saveOrupdateRoleAndRight(String rightids,RoleVo vo) throws Exception;
	
	public void saveOrupdateRoleByUserId(Long userid,String ids) throws Exception;
	
	public  List<Map<String, Object>> findRightTree() throws AjaxException;
	
	public  List<Map<String, Object>> findRightTreeByID(Long roleID) throws AjaxException;

}
