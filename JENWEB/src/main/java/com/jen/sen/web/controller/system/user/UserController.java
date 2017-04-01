package com.jen.sen.web.controller.system.user;

import java.util.HashMap;	
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jen.sen.commons.Constant;
import com.jen.sen.exception.AjaxException;
import com.jen.sen.persistence.core.dao.tools.PageDT;
import com.jen.sen.service.system.admin.ISystemService;
import com.jen.sen.service.system.user.IUserService;
import com.jen.sen.shiro.ShiroUtils;
import com.jen.sen.untils.DateUtils;
import com.jen.sen.untils.Util;
import com.jen.sen.web.component.ajaxComponent;
import com.jen.sen.web.vo.system.UserVo;

/**
 * 
 * filename UserController.java
 * company jen
 * @author jenson
 * @email jenson2000@sina.com
 */
@Controller
@RequestMapping(value = "system/user")
public class UserController {

	@Resource
	private IUserService iUserService;
	
	@Resource
	private ISystemService iSystemService;
	
	
	private final static String JSP_DIR = "system/user/";	
	

	@RequestMapping(value = "/addUser")	
	public ModelAndView userEditPage(HttpServletRequest request,ModelAndView mv) {
		
		mv.setViewName(JSP_DIR+"userEdit");
		return mv;
	}
	
	@RequestMapping(value = "/userManage")
	@RequiresPermissions("sys:user")
	public ModelAndView userManagePage(HttpServletRequest request,ModelAndView mv) {
		mv.setViewName(JSP_DIR+"userManage");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping(value ="/findUser")	
	public ajaxComponent<UserVo> findUser(HttpServletRequest request) throws AjaxException{
		ajaxComponent<UserVo> ajaxComponent = new ajaxComponent<UserVo>();
		
		String userAccount =  Util.getParameterString(request,"qyuserAccount");
		String userName =  Util.getParameterString(request,"qyuserName");
		String userStatus = Util.getParameterString(request,"qystatus");		
		String screatetime =  Util.getParameterString(request,"screateTime");
		String ecreatetime =  Util.getParameterString(request,"ecreatesTime");

		Map<String, Object>  parms= new HashMap<String, Object>();
		parms.put("userAccount", userAccount);
		parms.put("userName", userName);
		parms.put("userStatus", userStatus);
		parms.put("screatetime", screatetime);
		parms.put("ecreatetime", ecreatetime);
		
		int draw =Util.getParameterInt(request,"draw"); 
		int start = Util.getParameterInt(request,"start");
		int count=0;
	 	
			PageDT  pd = new PageDT(draw,start);
			count = iUserService.findUserPageCount(parms);
			pd.setRecordsFiltered(count);

			List<UserVo> userVoList = iUserService.findUserPage(parms, pd);
			ajaxComponent.setData(userVoList);
			ajaxComponent.setDraw(draw);
			ajaxComponent.setRecordsTotal(pd.getRecordsFiltered());
			ajaxComponent.setRecordsFiltered(pd.getRecordsFiltered());
		
		return ajaxComponent;
	}
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value ="/updatestate",method = RequestMethod.POST)	
	public ajaxComponent updateUserState(HttpServletRequest request) {
		
		String ids = Util.getParameterString(request,"userIds");			
			 try {
				 iUserService.updateUserState(ids);
			} catch (Exception e) {				
				throw new AjaxException();
			}
		 
			 
		return new ajaxComponent();
		
	}

	@ResponseBody
	@RequestMapping(value = "/saveOrUpdateUser",method=RequestMethod.POST)  
	public ajaxComponent<UserVo> saveOrUpdateUser(HttpServletRequest request)  {
		ajaxComponent<UserVo> ajaxComponent = new ajaxComponent<UserVo>();
		UserVo uv = new UserVo();
		try {
			Long id = Util.getParameterLong(request, "id");
					//Long.parseLong(request.getParameter("id")==null?"-1":request.getParameter("id"));			
						
			if(id>0){	
				uv = iUserService.findUser(id);
			}else{
				uv.setCreateTime(DateUtils.now());
			}
				uv.setUserAccount(Util.getParameterString(request,"iptaccount"));
				uv.setUserName(Util.getParameterString(request,"iptname"));
				uv.setUserSex(Short.parseShort(request.getParameter("rsex")==null?"1":request.getParameter("rsex")));
				uv.setUserIphone(Util.getParameterString(request,"iptphone"));
				uv.setUserBirthday(DateUtils.strToDateWithFormat(request.getParameter("iptborn"),"yyyy-MM-dd"));
				uv.setUserEmail(Util.getParameterString(request,"iptemail"));	
				uv.setUserAddress(Util.getParameterString(request,"iptaddress"));	
				uv.setRemark(Util.getParameterString(request,"iptremark"));	
				uv.setStatus((short)1);
				uv.setUserPassword(ShiroUtils.getpwdShiro("123456"));
				uv.setUpdateTime(DateUtils.now());
				
			
			iUserService.saveOrupdateUser(uv);
			
			ajaxComponent.setVoObject(uv);
		}  catch (Exception e) {
			throw AjaxException.create(Constant.SYSTEM_AJAX_Code, Constant.SYSTEM_ERROR_INFO);
		}
		
		
		return ajaxComponent;
	}	
	
	@ResponseBody
	@RequestMapping(value = "/queryUser")  
	public ajaxComponent<UserVo> queryUserbyID(HttpServletRequest request)  {
		Long id = Util.getParameterLong(request,"id");
				
		if(id<1){
			throw AjaxException.create(Constant.SYSTEM_AJAX_Code, Constant.SYSTEM_ERROR_INFO);
		}
		
		ajaxComponent<UserVo> ajaxComponent = new ajaxComponent<UserVo>();
		UserVo uv = new UserVo(); 
		
		uv=iUserService.findUser(id);
		ajaxComponent.setVoObject(uv);
		return ajaxComponent;
	}

}
