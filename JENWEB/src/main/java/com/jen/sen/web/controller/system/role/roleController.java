package com.jen.sen.web.controller.system.role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jen.sen.commons.Constant;
import com.jen.sen.exception.AjaxException;
import com.jen.sen.persistence.core.dao.tools.PageDT;
import com.jen.sen.service.system.role.IRoleService;
import com.jen.sen.untils.DateUtils;
import com.jen.sen.untils.Util;
import com.jen.sen.web.component.ajaxComponent;
import com.jen.sen.web.vo.system.RoleVo;
/**
 * 
 * filename roleController.java
 * company jen
 * @author jenson
 * @email jenson2000@sina.com
 */
@Controller
@RequestMapping(value = "system/role")
public class roleController {

	@Resource
	private IRoleService iRoleService;
	
	
	private final static String JSP_DIR = "system/role/";	
	

	@RequestMapping(value = "/roleEdit")	
	public ModelAndView roleEditPage(HttpServletRequest request,ModelAndView mv) {
		String id=Util.getParameterString(request,"id");
		String rdonly=Util.getParameterString(request,"rdonly");
		mv.addObject("roleId", id);
		mv.addObject("rdonly", rdonly);
		mv.setViewName(JSP_DIR+"roleEdit");
		return mv;
	}
	
	@RequestMapping(value = "/roleManage")
	@RequiresPermissions("sys:role")
	public ModelAndView roleManagePage(HttpServletRequest request,ModelAndView mv) {
		
		mv.setViewName(JSP_DIR+"roleManage");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value ="/findRole")	
	public ajaxComponent<RoleVo> findRole(HttpServletRequest request) throws AjaxException{
		ajaxComponent<RoleVo> ajaxComponent = new ajaxComponent<RoleVo>();
		
		String roleName =  Util.getParameterString(request,"qyroleName");
		String roleStatus = Util.getParameterString(request,"qystatus");		


		Map<String, Object>  parms= new HashMap<String, Object>();
		parms.put("roleName", roleName);
		parms.put("roleStatus", roleStatus);

		int draw = Util.getParameterInt(request,"draw"); 
		int start = Util.getParameterInt(request,"start");
		int count=0;
	 	
			PageDT  pd = new PageDT(draw,start);
			count = iRoleService.findRolePageCount(parms);
			pd.setRecordsFiltered(count);

			List<RoleVo> roleVoList = iRoleService.findRolePage(parms, pd);

			ajaxComponent.setData(roleVoList);
			ajaxComponent.setDraw(draw);
			ajaxComponent.setRecordsTotal(pd.getRecordsFiltered());
			ajaxComponent.setRecordsFiltered(pd.getRecordsFiltered());
		
		return ajaxComponent;
	}
	
	@ResponseBody
	@RequestMapping(value ="/findSelectRole")	
	public ajaxComponent<RoleVo> findAllRole(HttpServletRequest request) throws AjaxException{
		ajaxComponent<RoleVo> ajaxComponent = new ajaxComponent<RoleVo>();	
		Long UserId = Util.getParameterLong(request,"userid");
				
		if(UserId<1){
			throw AjaxException.create(Constant.SYSTEM_AJAX_Code, Constant.SYSTEM_ERROR_INFO);
		}
		
			List<RoleVo> roleVoList1 = iRoleService.findRole();
			List<RoleVo> roleVoList2 = iRoleService.findRoleByUserId(UserId);
			ajaxComponent.setData(roleVoList1);
			ajaxComponent.setDataSec(roleVoList2);
		
		
		return ajaxComponent;
	}
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value ="/updatestate",method = RequestMethod.POST)	
	public ajaxComponent updateroleState(HttpServletRequest request) {
		
		String ids = Util.getParameterString(request,"Ids");			
			 try {
				 iRoleService.updateRoleState(ids);
			} catch (Exception e) {				
				throw new AjaxException();
			}
		 
			 
		return new ajaxComponent();
		
	}

	@ResponseBody
	@RequestMapping(value = "/saveOrUpdateRole",method=RequestMethod.POST)  
	public ajaxComponent<RoleVo> saveOrUpdateRole(HttpServletRequest request)  {
		ajaxComponent<RoleVo> ajaxComponent = new ajaxComponent<RoleVo>();
		RoleVo uv = new RoleVo();
		try {
			Long id =Util.getParameterLong(request,"id");
			String rightids=Util.getParameterString(request,"rightIDS");
				if(id>0){	
					uv=iRoleService.findRole(id);					
				}else{
					uv.setCreateTime(DateUtils.now());	
				}
			
				uv.setRoleName(Util.getParameterString(request,"iptname"));
				uv.setStatus(new Short(request.getParameter("iptstatus")));					
				uv.setRemark(Util.getParameterString(request,"iptremark"));
				uv.setUpdateTime(DateUtils.now());				
				iRoleService.saveOrupdateRoleAndRight(rightids,uv);
			
			ajaxComponent.setVoObject(uv);
		}  catch (Exception e) {
			throw AjaxException.create(Constant.SYSTEM_AJAX_Code, Constant.SYSTEM_ERROR_INFO);
		}
		
		
		return ajaxComponent;
	}	
	
	@ResponseBody
	@RequestMapping(value = "/queryRole")  
	public ajaxComponent<RoleVo> queryRolebyID(HttpServletRequest request)  {
		Long id = Util.getParameterLong(request,"id");
		if(id<1){
			throw AjaxException.create(Constant.SYSTEM_AJAX_Code, Constant.SYSTEM_ERROR_INFO);
		}
		
		ajaxComponent<RoleVo> ajaxComponent = new ajaxComponent<RoleVo>();
		RoleVo uv = new RoleVo(); 
		
		uv=iRoleService.findRole(id);
		ajaxComponent.setVoObject(uv);
		return ajaxComponent;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/queryRoleByuserId")  
	public ajaxComponent<RoleVo> queryRolebyUserID(HttpServletRequest request)  {
		Long id = Util.getParameterLong(request,"userid");
		if(id<1){
			throw AjaxException.create(Constant.SYSTEM_AJAX_Code, Constant.SYSTEM_ERROR_INFO);
		}
		
		ajaxComponent<RoleVo> ajaxComponent = new ajaxComponent<RoleVo>();
		
		List<RoleVo> roleVoList=iRoleService.findRoleByUserId(id);
		ajaxComponent.setData(roleVoList);
		return ajaxComponent;
	}
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "/updateRoleByID",method=RequestMethod.POST)  
	public ajaxComponent updateRoleByUserId(HttpServletRequest request)  {
		ajaxComponent ajaxComponent = new ajaxComponent();
		try {			
			Long userId = Util.getParameterLong(request,"userId");
			
			String  roleids = Util.getParameterString(request,"ids");			
			if(userId>0){
				iRoleService.saveOrupdateRoleByUserId(userId, roleids);
			}			
			ajaxComponent.setMsg(Constant.SYSTEM_SUCCESS_INFO);
		}  catch (Exception e) {
			throw AjaxException.create(Constant.SYSTEM_AJAX_Code, Constant.SYSTEM_ERROR_INFO);
		}
		
		
		return ajaxComponent;
	}
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "/treeData",method=RequestMethod.POST)  
	public ajaxComponent Treedate(HttpServletRequest request)  {
		ajaxComponent<?> ajaxComponent = new ajaxComponent();
		try {
			Long roleId =Util.getParameterLong(request,"roleId");
			List<Map<String, Object>> lsm1=	iRoleService.findRightTree();
			List<Map<String, Object>> lsm2=	iRoleService.findRightTreeByID(roleId);
			//设置角色对应的权限为选中状态(树形菜单)
			for(Map<String, Object> map2:lsm2){
				for(Map<String, Object> map1:lsm1){
				if(map2.get("right_id").equals(map1.get("right_id"))){
					map1.put("checked", true);
					break;
				}				
			  }
			}

			ajaxComponent.setMap(lsm1);
			ajaxComponent.setMapSec(lsm2);
			
			ajaxComponent.setMsg(Constant.SYSTEM_SUCCESS_INFO);
		}  catch (Exception e) {
			throw AjaxException.create(Constant.SYSTEM_AJAX_Code, Constant.SYSTEM_ERROR_INFO);
		}
		
		
		return ajaxComponent;
	}

}
