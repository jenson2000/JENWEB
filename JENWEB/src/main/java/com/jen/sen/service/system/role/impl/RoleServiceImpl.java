package com.jen.sen.service.system.role.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.jen.sen.commons.BeanConstant;
import com.jen.sen.exception.AjaxException;
import com.jen.sen.persistence.core.dao.tools.PageDT;
import com.jen.sen.persistence.dao.system.IRoleDao;
import com.jen.sen.persistence.pojo.system.TRoles;
import com.jen.sen.service.system.role.IRoleService;
import com.jen.sen.web.vo.system.RoleVo;

@Service(value = BeanConstant.I_Role_SERVICE)
public class RoleServiceImpl implements IRoleService {
	@Resource
	private IRoleDao iRoleDaoimpl;

	@Override
	//@Cacheable(value = "sysrole", key = "'RoleServiceImpl_' + #Roleid")  
	public RoleVo findRole(Long Roleid) throws AjaxException{
		RoleVo RoleVo = new RoleVo();
		TRoles tu = new TRoles();
		tu = iRoleDaoimpl.findById(TRoles.class, Roleid);	
		RoleVo.toVo(tu);

		return RoleVo;
	}
	
	@Override
	//@Cacheable(value = "sysrole", key = "'RoleServiceImpl_findRoleByUserId_' + #UserId")  
	public List<RoleVo> findRoleByUserId(Long UserId) throws AjaxException{
		List<RoleVo> RoleVoList = new ArrayList<RoleVo>();
		List<TRoles> list = iRoleDaoimpl.queryRoleByUserID(UserId);				
				for (TRoles tu : list) {
					RoleVo RoleVo = new RoleVo();
					RoleVo.toVo(tu);
					RoleVoList.add(RoleVo);
				}			 
		
	

		return RoleVoList;
	}

	@Override
	public List<RoleVo> findRole() throws AjaxException{

		List<RoleVo> RoleVoList = new ArrayList<RoleVo>();
		List<TRoles> list = iRoleDaoimpl.findAll(TRoles.class);				
				for (TRoles tu : list) {
					RoleVo RoleVo = new RoleVo();
					RoleVo.toVo(tu);
					RoleVoList.add(RoleVo);
				}			 
		
		return RoleVoList;
	}

	@Override
	public List<RoleVo> findRolePage(Map<String, Object> parms, PageDT page) throws AjaxException{
	//	int count = iRoleDaoimpl.countListByHqlAndParas(sql, paras)
		
		List<TRoles> ls =  iRoleDaoimpl.queryPage(parms, page);

		List<RoleVo> VoList = new ArrayList<RoleVo>();
		for (TRoles ts : ls) {
			RoleVo uv = new RoleVo();
			uv.toVo(ts);
			VoList.add(uv);
		}
		
		return VoList;
	}

	@Override
	public int findRolePageCount(Map<String, Object> parms) throws AjaxException {
		int count = iRoleDaoimpl.queryRolesCount(parms);
		return count;
	}

	@Override
	//@CacheEvict(value = "sysrole",allEntries=true)  
	public void updateRoleState(String ids) throws AjaxException {
		iRoleDaoimpl.updateRolestate(ids);
		
	}

	@Override
	//@CachePut(value = "sysrole", key = "'RoleServiceImpl_' + #vo.roleId")   
	public void saveOrupdateRole(RoleVo vo) throws Exception {
		iRoleDaoimpl.saveOrupdateRoles(vo.toPojo());
	}
	
  
	public void saveOrupdateRoleAndRight(String rightids,RoleVo vo){
		iRoleDaoimpl.saveOrupdateRoleAndRight(rightids, vo.toPojo());
	}

	@Override
	public void saveOrupdateRoleByUserId(Long userid, String ids) throws Exception {
		iRoleDaoimpl.saveOrupdateRoleByUserId(userid, ids);	
	}

	@Override
	public  List<Map<String, Object>> findRightTree() throws AjaxException {
		return iRoleDaoimpl.queryRightTree();
	}

	@Override
	public List<Map<String, Object>> findRightTreeByID(Long roleID) throws AjaxException {
		return iRoleDaoimpl.queryRightTreeById(roleID);
	}


}
